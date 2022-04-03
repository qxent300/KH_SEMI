package com.kh.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;


/**
 * 파일 이름을 변경하기 위한 규칙 재정의
 * -> 한글처리로 인해서 파일명을 한글 아닌 파일로 변경
 */
public class MyFileRenamePolicy implements FileRenamePolicy{

	private static int sequenceNumber = 0;
	
	@Override
	public File rename(File oldFile) {
		// 파일 이름을 삭제, 일자와 시간, 고유번호로 이름을 재생성
		// 오래된 파일의 경우 주기적 삭제 또는 경로 변경이 필요
		
		String dateStr = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
		String sequenceStr = "" + (sequenceNumber++);
		String randoStr = "" + new Random().nextInt(1000);
		String extensionStr = ""; // 확장자 .exe .pdf .ppt

		try {
			String oldName = oldFile.getName(); // test.pdf a.out
			int extensionIndex = oldName.lastIndexOf(".");
			extensionStr = oldName.substring(extensionIndex, oldName.length());
		} catch (Exception e) {}

		
		String newFileName = dateStr + "_" +sequenceStr + "_" + randoStr + extensionStr;
		File newFile = new File(oldFile.getParent(), newFileName);
		
		return newFile;
	}

	//	public static void main(String[] args) {
//		File file1 = new File("./text.ertret.rwrwr.txt");
//		File file2 = new File("./text.ertret.rwrwr.txt");
//		File file3 = new File("./text.ertret.rwrwr.txt");
//		File file4 = new File("./abcd");
//		
//		File renameFile1 = new MyFileRenamePolicy().rename(file1);
//		File renameFile2 = new MyFileRenamePolicy().rename(file2);
//		File renameFile3 = new MyFileRenamePolicy().rename(file3);
//		File renameFile4 = new MyFileRenamePolicy().rename(file4);
//		
//		System.out.println(renameFile1);
//		System.out.println(renameFile2);
//		System.out.println(renameFile3);
//		System.out.println(renameFile4);
//	}
}
