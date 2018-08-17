package com.alpha.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeUtil {

	public SerializeUtil() {
		// TODO Auto-generated constructor stub
	}
    public static List<?> unserializeList(byte[] bytes) {  
        if (bytes == null) {  
            return null;  
        }  
  
        List<Object> list = new ArrayList<Object>();  
        ByteArrayInputStream bais = null;  
        ObjectInputStream ois = null;  
        try {  
            // 反序列化  
            bais = new ByteArrayInputStream(bytes);  
            ois = new ObjectInputStream(bais);  
            while (bais.available() > 0) {  
                Object obj = (Object) ois.readObject();  
                if (obj == null) {  
                    break;  
                }  
                list.add(obj);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return list;  
    } 
    
    
    
    public static Object unserializeObj(byte[] bytes) {  
        if (bytes == null) {  
            return null;  
        }  
  
        List<Object> list = new ArrayList<Object>();  
        ByteArrayInputStream bais = null;  
        ObjectInputStream ois = null;  
        try {  
            // 反序列化  
            bais = new ByteArrayInputStream(bytes);  
            ois = new ObjectInputStream(bais);  
            Object obj = (Object) ois.readObject(); 
            return obj;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        }  
        
    }
	
	
	
    public static byte[] serializeList(List<?> list) {  
    	  
        if (list.isEmpty()) {  
            return null;  
        }  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        byte[] bytes = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            for (Object obj : list) {  
                oos.writeObject(obj);  
            }  
            bytes = baos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	try {
				oos.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }  
        return bytes;  
    } 
    
    
    
    public static byte[] serializeObj(Object object) {  
  	  
//        if (list.isEmpty()) {  
//            return null;  
//        }  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        byte[] bytes = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            oos.writeObject(object);  

            bytes = baos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	try {
				oos.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }  
        return bytes;  
    }  
}
