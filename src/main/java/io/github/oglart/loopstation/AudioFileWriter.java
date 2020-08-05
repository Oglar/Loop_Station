package io.github.oglart.loopstation;

import javax.sound.sampled.AudioFileFormat;

public interface AudioFileWriter {
    void write(String filename, AudioFileFormat.Type type, AudioRecorder.AudioRecorderResult result);

    default void write(String filename, AudioRecorder.AudioRecorderResult result) {
        this.write(filename, AudioFileFormat.Type.WAVE, result);
    }
}
