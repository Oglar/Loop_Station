package io.github.oglart.loopstation;

import javax.sound.sampled.*;
import java.io.*;

public class AudioRecorderImpl implements AudioRecorder {

    @Override
    public AudioRecorder.AudioRecorderResult record() {

        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        TargetDataLine microphone;
        AudioInputStream audioInputStream;
        SourceDataLine sourceDataLine;
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            int CHUNK_SIZE = 1024;
            byte[] data = new byte[microphone.getBufferSize() / 5];
            microphone.start();

            int bytesRead = 0;

            try {
                while (bytesRead < 100000) { // Just so I can test if recording
                    // my mic works...
                    numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                    bytesRead = bytesRead + numBytesRead;
                    System.out.println(bytesRead);
                    out.write(data, 0, numBytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte audioData[] = out.toByteArray();

            // Get an input stream on the byte array
            // containing the data
            InputStream byteArrayInputStream = new ByteArrayInputStream(
                    audioData);
            audioInputStream = new AudioInputStream(byteArrayInputStream,format, audioData.length / format.getFrameSize());
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(format);
            sourceDataLine.start();
            int cnt = 0;
            byte[] tempBuffer = new byte[10000];
            try {
                while ((cnt = audioInputStream.read(tempBuffer, 0,tempBuffer.length)) != -1) {
                    if (cnt > 0) {
                        // Write data to the internal buffer of
                        // the data line where it will be
                        // delivered to the speaker.
                        sourceDataLine.write(tempBuffer, 0, cnt);
                    }// end if
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Block and wait for internal buffer of the
            // data line to empty.
            sourceDataLine.drain();
            sourceDataLine.close();
            microphone.close();

            AudioRecorderResult result = new AudioRecorderResult();
            result.setFormat(format);
            result.setContent(audioData);
            return result;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }

}
