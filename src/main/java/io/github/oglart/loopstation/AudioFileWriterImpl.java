package io.github.oglart.loopstation;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioFileWriterImpl implements AudioFileWriter {
    @Override
    public void write(String filename, AudioFileFormat.Type type, AudioRecorder.AudioRecorderResult result) {
        byte audioData[] = result.getContent();
        AudioFormat format = result.getFormat();

        long length = audioData.length / format.getFrameSize();

        try (final FileOutputStream fileOutputStream = new FileOutputStream(filename);
             final AudioInputStream audioInputStream1 = new AudioInputStream(
                     new ByteArrayInputStream(audioData), format, length)){
            AudioSystem.write(audioInputStream1, type, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
