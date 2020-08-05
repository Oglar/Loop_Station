package io.github.oglart.loopstation;

public class Main {

    public static void main (String[] args){
        System.out.println("Hello World!");

        AudioRecorder audioRecorder = new AudioRecorderImpl();
        AudioFileWriter audioFileWriter = new AudioFileWriterImpl();

        AudioRecorder.AudioRecorderResult audioRecorderResult = audioRecorder.record();

        audioFileWriter.write("Output.wav", audioRecorderResult);
    }

}
