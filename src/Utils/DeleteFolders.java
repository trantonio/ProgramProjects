package Utils;

import java.io.File;

public class DeleteFolders {

    public static void deleteFolder(File fileDel) {
        if(fileDel.isDirectory()){
            if(fileDel.list().length == 0)
                fileDel.delete();
            else{
                for (String temp : fileDel.list()) {
                    File fileDelete = new File(fileDel, temp);
                    //recursive delete
                    deleteFolder(fileDelete);
                }
                //check the directory again, if empty then delete it
                if(fileDel.list().length==0)
                    fileDel.delete();
            }
        }else{
            //if file, then delete it
            fileDel.delete();
        }
    }
}
