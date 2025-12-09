/**
 * 验证码图片生成
 * 供图片输出类 VilidationCodeResult 使用
 * 王为 2010.4.22
 */

package com.luyuan.sys.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.SessionAware;
//import com.opensymphony.xwork2.Action;
import com.sun.image.codec.jpeg.ImageFormatException;

import com.luyuan.action.BaseAct;

public class ValidationCodeAct extends BaseAct implements SessionAware{

	private Map session;
	
	public void setSession(Map session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
	private static final int WIDTH = 60;
	private static final int HEIGHT = 20;
	private static final int CODE_LEN = 4;
	private static final char[] useChar = new char[]{
		'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R',
		'S','T','U','V','W','X','Y','Z','2','3','4','5','6','7','8','9'
	};
	
	private byte[] imageBytes;
	
	private Color getColor(int fc, int bc){
		Random rd = new Random();
		if (fc > 255) fc = 200;
		if (bc > 255) bc = 255;
		int r = fc + rd.nextInt(bc - fc);
		int g = fc + rd.nextInt(bc - fc);
		int b = fc + rd.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	//图片生成
	private void saveImage() throws ImageFormatException, IOException {
		int fHeight = HEIGHT - 2;
		int bWidth = WIDTH - 1;
		int bHeight = HEIGHT - 1;
		int offsetWidth = WIDTH/(CODE_LEN + 1);
		int addition = offsetWidth/2;
		int codeHeight = HEIGHT - 4;
		
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		
		g.setColor(getColor(200,250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Font font = new Font("Times New Roman", Font.PLAIN, fHeight);
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, bWidth, bHeight);
		
		//160条随机干扰线
		Random rd = new Random();
		g.setColor(getColor(160, 200));
		for(int i = 0; i<160; i++){
			int x = rd.nextInt(WIDTH);
			int y = rd.nextInt(HEIGHT);
			int x1 = rd.nextInt(12);
			int y1 = rd.nextInt(12);
			g.drawLine(x, y, x+x1, y+y1);
		}
		
		StringBuffer sb = new StringBuffer();
		int red=0, green=0, blue=0;
		for(int i=0; i< CODE_LEN; i++){
			int index = rd.nextInt(31);
			String sr = String.valueOf(useChar[index]);
			sb.append(sr);
			red = rd.nextInt(110);
			green = rd.nextInt(50);
			blue = rd.nextInt(50);
			g.setColor(new Color(20+red,20+green,20+blue));
			g.drawString(sr, offsetWidth*i + addition, codeHeight);
		}
		//验证码存入
		session.put("vCode", sb.toString());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpeg", baos);
		imageBytes = baos.toByteArray();
		baos.close();
	}
	
	@Override	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		saveImage();
		return SUCCESS;
	}
	
	//向自定义结果返回图像类型
	public String getContentType(){
		return "image/jpeg";
	}

	//向自定义结果返回图像数据长度
	public int getContentLength(){
		return imageBytes.length;
	}
	
	//向自定义结果返回图像
	public byte[] getImageInBytes(){
		return imageBytes;
	}
}
