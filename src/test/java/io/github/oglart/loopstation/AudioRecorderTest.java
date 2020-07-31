package io.github.oglart.loopstation;

import io.github.oglart.loopstation.AudioRecorder.AudioRecorderResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class AudioRecorderTest {

    @Test
    public void itShouldRecordAudioSuccessfully() {
        AudioRecorder sut = new AudioRecorder();

        AudioRecorderResult result = sut.record();

        assertThat(result, is(notNullValue()));
    }
}