import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



public class InterfaceOfAllAPIs {

	public InterfaceOfAllAPIs(){
	}
	
	
	
	
	public static byte[] convertImageToByte(File f)
	{
		if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];//b is the buffer where the data is read
            int n;
            while ((n = stream.read(b)) != -1)//the total number of bytes read into the buffer, or -1 if there is no more data because the end of the file has been reached.
                out.write(b, 0, n);//write the data (which is read into buffer b) to the byteArrayOutputStream out 
            stream.close();
            out.close();
            return out.toByteArray();//convert out stream to byteArray
        } catch (IOException e) {
        }
        return null;
	}
		
	public static String detect(byte[] filebyte)
	{
		CommonOperate cmo = CommonOperate.getInstance();
		//detect api
		Response detectResult = null;
		try {
			detectResult = cmo.detectByte(filebyte, 1, "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			
		//start to take out the face_token of detectAPI
		String str = new String(detectResult.getContent());//convert the result into string 
				
		JSONObject jso = JSONObject.parseObject(str);
		JSONArray jsonArray = jso.getJSONArray("faces");
		
		if(jsonArray.isEmpty())
		{
			return Key.KEY_FOR_DETECT_FAILED_MESSAGE;
		}
		else
			{
			String face_token=null;
		    for(int i= 0;i<jsonArray.size();i++)
		      {
			     JSONObject jsonObject = jsonArray.getJSONObject(i);
			     face_token = jsonObject.getString("face_token");
		      }
		return face_token;
		}
		
	}

	public static String compareTwoFaces(byte[] filebyte1, byte[] filebyte2)	{
        CommonOperate cmo = CommonOperate.getInstance();
		Response compareResult = null;
		try {
			compareResult = cmo.compare(detect(filebyte1), null, null, null, detect(filebyte2), null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str = new String(compareResult.getContent());
		JSONObject jso = JSONObject.parseObject(str);
		
		
		return "the posibility of the same person among these two pictures is"+" "+jso.getString("confidence")+"%";
		
	}
	
	

	public static String createLocalFaceSet(String faceSetName, String faceSetId)
	{
        FaceSetOperate fso = FaceSetOperate.getInstance();
        Response fs = null;
		try {
			fs = fso.createFaceSet(faceSetName, faceSetId, null, null, null, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new String(fs.getContent());
		JSONObject jso = JSONObject.parseObject(str);
		
		
		String faceSetMessage = "the name of our set is: "+ faceSetName + "\n" 
		                       +"the identifier of our set is: " + jso.getString("outer_id") + "\n"
		                       +"the facesetToken(set key) of our set is "+ jso.getString("faceset_token")+"\n"
		                       +"the number of faces in our set is: "+ jso.getString("face_count") + "\n"
		                       +"faceset creation completed\n";
		return faceSetMessage;
	}
	
	public static String addOneFaceIntoFaceSet(String faceToken, String faceSetId)
	{
		FaceSetOperate fso = FaceSetOperate.getInstance();
        Response fs = null;
		try {
			fs = fso.addFaceByOuterId(faceToken, faceSetId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new String(fs.getContent());
		JSONObject jso = JSONObject.parseObject(str);
		
		String facesetMessage = "the identifier of our set is: " + jso.getString("outer_id") + "\n"
				               +"the facesetToken(set key) of our set is "+ jso.getString("faceset_token")+"\n"
				               +"the number of faces added this time is: "+ jso.getString("face_added") + "\n"
				               +"the number of faces in our set is: "+ jso.getString("face_count") + "\n";
		return facesetMessage;
		
	}
	
    public static String deleteLocalFaceSet(String faceSetId)
	{
		FaceSetOperate fso = FaceSetOperate.getInstance();
        Response fs = null;
		try {
			fs = fso.deleteFaceSetByOuterId(faceSetId, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new String(fs.getContent());
		JSONObject jso = JSONObject.parseObject(str);
		
		
		String faceSetMessage = "the identifier of our set is: " + jso.getString("outer_id") + "\n"
		                       +"the facesetToken(set key) of our set is "+ jso.getString("faceset_token")+"\n"
		                       +"faceset delete completed\n";
		                 
		return faceSetMessage;
		
	}
    
    public static String deleteOneFaceFromFaceSet(String faceToken, String faceSetId)
    {
    	FaceSetOperate fso = FaceSetOperate.getInstance();
        Response fs = null;
		try {
			fs = fso.removeFaceFromFaceSetByOuterId(faceSetId, faceToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new String(fs.getContent());
		JSONObject jso = JSONObject.parseObject(str);
		
		String facesetMessage = "the identifier of our set is: " + jso.getString("outer_id") + "\n"
	               +"the facesetToken(set key) of our set is "+ jso.getString("faceset_token")+"\n"
	               +"the number of faces in our set is: " + jso.getString("face_removed") +"\n"
	               +"the number of faces in our set is: "+ jso.getString("face_count") + "\n";
		return facesetMessage;
    }
	
    public static String getDetailsOfFaceSet(String faceSetId)
    {
    	FaceSetOperate fso = FaceSetOperate.getInstance();
        Response fs = null;
        
        try {
			fs = fso.getDetailByOuterId(faceSetId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String str = new String(fs.getContent());
		JSONObject jso = JSONObject.parseObject(str);
		JSONArray jsonArray = jso.getJSONArray("face_tokens");
		String face_tokens="the facetokens are ";
		if(jsonArray.isEmpty())
		{
			face_tokens +="empty";
		}
		else
			{
			face_tokens +="like:\n";

		    for(int i= 0;i<jsonArray.size();i++)
		      {
			     String temp = (String)jsonArray.get(i);
			     face_tokens += temp + "\n";
		      }
		}
		
		String facesetMessage = 
				               "the name of our set is: "+ jso.getString("display_name") + "\n"
				               +"the facesetToken(set key) of our set is "+ jso.getString("faceset_token")+"\n"
				               +"the identifier of our set is: " + jso.getString("outer_id") + "\n"
				               +face_tokens
				               +"the number of faces in our set is: "+ jso.getString("face_count") + "\n";
        
    	return facesetMessage;
    	
    }
    
    public static String setUserIdForFaceToken(String faceToken, String userId)
    {
    	FaceOperate fo = FaceOperate.getInstance();
        Response fs = null;
        
        if(faceToken != Key.KEY_FOR_DETECT_FAILED_MESSAGE)
        {
            try {
			  fs = fo.faceSetUserId(faceToken, userId);
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
            String str = new String(fs.getContent());
		    JSONObject jso = JSONObject.parseObject(str);
    	
		    String faceMessage = "the facetoken "+jso.getString("face_token") 
		                      + "\'s userid is "+jso.getString("user_id")+"\n";
    	    return faceMessage;
        }
        else
        {
        	return Key.KEY_FOR_DETECT_FAILED_MESSAGE;
        }
    	
    }
    
    public static String searchForUserId(byte[] filebyte, String faceSetId)
    {
    	String faceToken = detect(filebyte);
    	CommonOperate cmo = CommonOperate.getInstance();
    	Response rs = null;
        if(faceToken != Key.KEY_FOR_DETECT_FAILED_MESSAGE) 
        {
		   try {
			rs = cmo.searchByOuterId(faceToken, null, null, faceSetId, 1);
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		   }
        
          String str = new String(rs.getContent());
		  JSONObject jso = JSONObject.parseObject(str);
		  JSONArray jsonArray = jso.getJSONArray("results");
		  String theConfidence = null;
		  String theUserId = null;
		  String theFaceToken = null;	
		  
		  JSONObject jsonObject = jsonArray.getJSONObject(0);
		  theConfidence = jsonObject.getString("confidence");
		  theUserId = jsonObject.getString("user_id");
		  theFaceToken = jsonObject.getString("face_token");
		  
		  /*String searchMessage = "the best suit userid is "+theUserId +"\n"
		                        +"the conresponding posibility is " +theConfidence +"\n"
				                +"the conresponding facetoken is "+ theFaceToken+"\n";*/


		  if(Float.parseFloat(theConfidence)>80.0)
		  {
			  return theUserId;
		  }
		  else
		  {
		  	return Key.KEY_FOR_SEARCH_MATCHFAILED_MESSAGE;
		  }

    	  
        }
        else
        {
        	return Key.KEY_FOR_DETECT_FAILED_MESSAGE;
        }
    	
    }


}
