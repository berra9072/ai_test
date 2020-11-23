package com.aircode.admin.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.List;

import com.aircode.admin.common.PropertiesConfiguration;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AWSUtil {

	private AmazonS3 s3Conn;

	public AWSUtil() {
		s3Conn = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(new ProfileCredentialsProvider()).build();
	}

	/**
	 *
	 * 버킷 리스트 조회
	 *
	 * @return
	 */
	public List<Bucket> getBucketList() {
		log.info(" [AWS S3] Bucket List.");
		return s3Conn.listBuckets();
	}
	/**
	 *
	 * 버킷 생성
	 *
	 * @param bucketName
	 * @return
	 */
	public Bucket createBucket(String bucketName) {
		log.info(" [AWS S3] Bucket : {} Create.", bucketName);
		return s3Conn.createBucket(bucketName);
	}

	/**
	 *
	 * 폴더 생성
	 * 폴더는 폴더명 뒤에 "/" 붙여야 함.
	 *
	 * @param bucketName
	 * @param folderName
	 */
	public void createFolder(String bucketName, String folderName) {
		s3Conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
		log.info(" [AWS S3] Bucket({}) In Folder : {} Create.", bucketName, folderName);
	}

	/**
	 *
	 * 멀티파트 파일 업로드 : TransferManager 사용
	 *
	 * @param bucketName
	 * @param uploadPath
	 * @param file
	 * @return
	 */
	public boolean multipartUpload(String bucketName, String uploadPath, File file) {
		log.info(" [AWS S3] Bucket({}) In uploadPath : {} File Upload Start.", bucketName, uploadPath);

		boolean resultFlag = false;
		TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3Conn).build();
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadPath, file);
			putObjectRequest.setGeneralProgressListener(new ProgressListener() {
				long fileSize = file.length();

				long uploadSize = 0;
				int uploadPct = 0;
				int comparePct = 0;

				@Override
				public void progressChanged(ProgressEvent progressEvent) {
					long transferSize = progressEvent.getBytesTransferred();
					uploadSize = uploadSize + transferSize;
					uploadPct = Math.round((float) uploadSize / fileSize * 100);

					if(transferSize > 0 && uploadPct > comparePct) {
						log.debug(" [AWS S3] Multipart Upload filseZie : {}, uploadSize : {}", fileSize, uploadSize);
						log.info(" [AWS S3] Multipart Upload Progress : {}%", uploadPct);
					}

					comparePct = uploadPct;
				}
			});

			Upload upload = tm.upload(putObjectRequest);
			upload.waitForCompletion();

			resultFlag = true;
		} catch (AmazonServiceException e) {
			resultFlag = false;
			log.error(CommonUtil.makeStackTrace(e));
		} catch (AmazonClientException e) {
			resultFlag = false;
			log.error(CommonUtil.makeStackTrace(e));
		} catch (InterruptedException e) {
			resultFlag = false;
			log.error(CommonUtil.makeStackTrace(e));
		} finally {
			tm.shutdownNow();
		}

		log.info(" [AWS S3] Bucket({}) In uploadPath : {} File Upload End.", bucketName, uploadPath);

		return resultFlag;
	}

	/**
	 *
	 * 멀티파트 업로드 중단
	 *
	 * @param bucketName
	 */
	public void abortMultipartUpload(String bucketName) {
		log.info(" [AWS S3] Bucket({}) MultipartUpload Abort Start.", bucketName);

		TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3Conn).build();

		long sevenDays = 1000 * 60 * 60 * 24 * 7;
		Date oneWeekAgo = new Date(System.currentTimeMillis() -  sevenDays);
		tm.abortMultipartUploads(bucketName, oneWeekAgo);

		log.info(" [AWS S3] Bucket({}) MultipartUpload Abort End.", bucketName);
	}

	/**
	 *
	 * 파일 삭제
	 *
	 * @param bucketName
	 * @param fileName
	 */
	public void fileDelete(String bucketName, String fileName) {
		s3Conn.deleteObject(bucketName, fileName.replace(File.separatorChar, '/'));
		log.info(" [AWS S3] Bucket({}) In File : {} Delete.", bucketName, fileName);
	}

	/**
	 *
	 * AWS CloudFront 서비스 사용가능한 URL 정보 조회
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String getAWSCloudFrontUrl(String fileName) throws Exception {
		log.info(" [AWS CloudFront] File : {} URL.", fileName);
		return PropertiesConfiguration.getString("hosts.properties", "aws.cloudfront.domain") + fileName;
	}

}
