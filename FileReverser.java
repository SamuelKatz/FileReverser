import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class FileReverser {
    private String filename;
    private String inpath;
    private String outpath;
    public FileReverser(String filename){
        this.filename = filename;
        this.inpath = FileReverser.class.getResource(filename).getFile();
        this.outpath = this.inpath.substring(0, this.inpath.indexOf(this.filename)) + "rev" + this.filename;
        //System.out.println((this.filename + "\n" + this.inpath + "\n" + this.outpath));
    }
    public void reverser(){
        try (RandomAccessFile raf = new RandomAccessFile(this.inpath, "r");
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(this.outpath))){
            long x = raf.length();
            System.out.println(this.filename+" is the file to be reversed. It is "+x+" bytes in size.");
            byte[] arr = new byte[(int)x];
            raf.read(arr);
            revArr(arr);
            bout.write(arr, 0, arr.length);
        }
        catch(FileNotFoundException fnf){
            System.out.println("File not found.");
            fnf.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private static void revArr(byte[] arr){
        int i = 0;
        int j = arr.length-1;
        while(i<j){
            byte temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter file to be reversed, then press enter.  ");
        String fname = scan.nextLine();
        FileReverser fr = new FileReverser(fname);
        Instant start = Instant.now();
        fr.reverser();
        Instant stop = Instant.now();
        long timeElapsed = Duration.between(start, stop).toMillis();
        System.out.println("rev"+fname+" is the reversed file. Time to reverse was "+timeElapsed+" ms");
    }

}
