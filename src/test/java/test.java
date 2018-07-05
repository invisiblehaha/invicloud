import java.io.ByteArrayOutputStream;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class test {

    public static void main(String[] args) throws Exception {


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        fixedThreadPool.execute(new Runnable() {
            public void run() {
				/*File file = new File("C:\\Users\\贺俞凯\\Desktop\\example.jpg");
				byte[] fileByte =  convertImageToByte(file);//convert image file into byte[]
				
				File file1 = new File("C:\\Users\\贺俞凯\\Desktop\\example1.jpg");
				byte[] fileByte1 = convertImageToByte(file1);
				
				File file2 = new File("C:\\Users\\贺俞凯\\Desktop\\example2.jpg");
				byte[] fileByte2 = convertImageToByte(file2);
				
				File file3 = new File("C:\\Users\\贺俞凯\\Desktop\\nonhuman.jpg");
				byte[] fileByte3 = convertImageToByte(file3);
				
				
				
				//detect face
				String face_token = detect(fileByte);
				String face_token1 = detect(fileByte1);
				String face_token2 = detect(fileByte2);
				String face_token3 = detect(fileByte3);
				
				
				//compare two faces
				//String compareResult = compareTwoFaces(fileByte1,fileByte2);//Http request
				
				//faceset operation
				//the String varibles can be deleted and use the function only, just need to modify the return type of these functions
				
				String createFaceSetResult = createLocalFaceSet("CRMS Human Face Set", "FS_1");//create a faceset and store the status of the creation into a string
				String setUserIdForFaceTokenResult = setUserIdForFaceToken(face_token,"face_0");
				String addOneFaceIntoFaceSetResult = addOneFaceIntoFaceSet(face_token, "FS_1");
				String setUserIdForFaceTokenResult1 = setUserIdForFaceToken(face_token1,"face_1");
				String addOneFaceIntoFaceSetResult1 = addOneFaceIntoFaceSet(face_token1, "FS_1");
				String setUserIdForFaceTokenResult2 = setUserIdForFaceToken(face_token2,"face_2");
				String addOneFaceIntoFaceSetResult2 = addOneFaceIntoFaceSet(face_token2, "FS_1");
				String setUserIdForFaceTokenResult3 = setUserIdForFaceToken(face_token3,"face_3");
				String addOneFaceIntoFaceSetResult3 = addOneFaceIntoFaceSet(face_token3, "FS_1");
				//String deleteFaceSetResult = deleteLocalFaceSet("FS_1");//delete the corresponding faceset and store the status of the delete into a string
				
		        
				//System.out.println(face_token3);//detect result: return a facetoken of the face that is detected in a given picture
		        //System.out.println(compareResult);//compare result: return a string describing the posibility of the same person in two given pictures
		        System.out.println(createFaceSetResult);//create faceset result: return the status of the faceset just created
		        System.out.println(setUserIdForFaceTokenResult);
		        System.out.println(addOneFaceIntoFaceSetResult);//add result: add one face into faceset
		        
		        System.out.println(setUserIdForFaceTokenResult1);
		        System.out.println(addOneFaceIntoFaceSetResult1);
		        
		        System.out.println(setUserIdForFaceTokenResult2);
		        System.out.println(addOneFaceIntoFaceSetResult2);
		        
		        System.out.println(setUserIdForFaceTokenResult3);
		        System.out.println(addOneFaceIntoFaceSetResult3);
		        System.out.println(getDetailsOfFaceSet("FS_1"));
				//System.out.println(deleteFaceSetResult);//delete faceset result: return the status of the faceset just deleted
*/



				InterfaceOfAllAPIs apis = new InterfaceOfAllAPIs();

				File file4 = new File("C:\\Users\\贺俞凯\\Desktop\\example1-1.jpg");
				byte[] fileByte4 = convertImageToByte(file4);

				System.out.println(apis.getDetailsOfFaceSet("FS_1"));
				System.out.println(apis.searchForUserId(fileByte4, "FS_1"));

            }
        });

    }


    public static byte[] convertImageToByte(File f) {
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

}


	
	