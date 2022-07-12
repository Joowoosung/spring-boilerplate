package com.jws.settings.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Patrick on 2016. 12. 13..
 */
public class EncryptUtil implements Serializable {

  public final static String AES_KEY_CODE = "oX4oXfIJhRqZ2DWGhngFiOjkm4HY50RB";
  public final static String AES_IV_CODE = "R40Ip3TfmVQW9Gic";
  private static final long serialVersionUID = 1L;

  public static String excludeSimilarRandomPassword(int size) {
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();

    String[] chars = "A,B,C,D,E,F,G,H,J,K,M,N,P,Q,R,S,T,U,V,W,X,Y,Z,1,2,3,4,5,6,7,8,9".split(",");

    for (int i = 0; i < size; i++) {
      buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
  }

  public static String randomPassword(int size) {
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();

    String[] chars = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9"
        .split(",");

    for (int i = 0; i < size; i++) {
      buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
  }

  public static String randomPasswordOnlyNumber(int size) {
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();

    String[] chars = "0,1,2,3,4,5,6,7,8,9".split(",");

    for (int i = 0; i < size; i++) {
      buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
  }

  public static String AesEncrypt(String text)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    if (text == null || text.length() == 0) {
      return text;
    }
    String encrypted = null;
    byte[] source = text.getBytes(StandardCharsets.UTF_8);
    SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY_CODE.getBytes(StandardCharsets.UTF_8), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

    AlgorithmParameterSpec IVspec = new IvParameterSpec(AES_IV_CODE.getBytes(StandardCharsets.UTF_8));
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IVspec);
    int mod = source.length % 16;
    if (mod != 0) {
      text = String.format(text + "%" + (16 - mod) + "s", " ");
    }
    encrypted = byteArrayToHex(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
    return encrypted;
  }

  public static String AesDecrypt(String s)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    if (s == null || s.length() == 0) {
      return s;
    }
    String decrypted = null;
    SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY_CODE.getBytes(StandardCharsets.UTF_8), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

    AlgorithmParameterSpec IVspec = new IvParameterSpec(AES_IV_CODE.getBytes(StandardCharsets.UTF_8));
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, IVspec);
    decrypted = new String(cipher.doFinal(hexToByteArray(s)), StandardCharsets.UTF_8);
    return decrypted.trim();
  }

  private static byte[] hexToByteArray(String s) {
    byte[] retValue = null;
    if (s != null && s.length() != 0) {
      retValue = new byte[s.length() / 2];
      for (int i = 0; i < retValue.length; i++) {
        retValue[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
      }
    }
    return retValue;
  }

  private static String byteArrayToHex(byte[] buf) {
    StringBuffer strbuf = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      strbuf.append(String.format("%02x", buf[i]));
    }

    return strbuf.toString();
  }


  public static String MD5(String md5) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100), 1, 3);
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String SHA256(String value) throws NoSuchAlgorithmException {
    String encryptData = "";

    MessageDigest sha = MessageDigest.getInstance("SHA-256");
    sha.update(value.getBytes());

    byte[] digest = sha.digest();
    for (int i = 0; i < digest.length; i++) {
      encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
    }

    return encryptData;
  }
}
