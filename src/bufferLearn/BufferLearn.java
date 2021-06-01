package bufferLearn;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BufferLearn {

    @Test
    public void writeTest() throws IOException {
        Random r = new Random();
        String filename = "filename";
//        var file =new FileOutputStream(filename);
        var file = new BufferedOutputStream(new FileOutputStream(filename));
        long start = System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            for (int j=0;j<5;j++){
                file.write(97+r.nextInt(5));
            }
        }
        file.close();
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void readTest() throws IOException{
        String filename = "filename";
        var file = new BufferedInputStream(new FileInputStream(filename));
        long start = System.currentTimeMillis();

        while ((file.read())!=-1){

        }
        System.out.println(System.currentTimeMillis()-start);
        file.close();
    }

    @Test
    public void readTestNio() throws IOException{
        String filename = "filename";
        var fileChannel = new FileInputStream(filename).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8*1024);
        var start = System.currentTimeMillis();
        while(fileChannel.read(buffer) !=-1){
            buffer.flip();
            System.out.println(new String(buffer.array()));
            buffer.clear();
        }
        System.out.format("%dms\n",System.currentTimeMillis()-start);

    }

    @Test
    public void readTestAsync() throws IOException, ExecutionException, InterruptedException {
        String filename = "filename";
        var fileChannel= AsynchronousFileChannel.open(Path.of(filename), StandardOpenOption.READ);

        var buffer = ByteBuffer.allocate(1024*8);
        Future<Integer> operation = fileChannel.read(buffer,0);
        var numReads = operation.get();
        buffer.flip();
        System.out.println(new String(buffer.array()));
        buffer.clear();
    }

    @Test
    public void testChinese(){
        var raw = "";
        var charset = StandardCharsets.UTF_8;
        var bytes = charset.encode(raw).array();

    }
}
