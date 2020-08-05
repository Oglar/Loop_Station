package io.github.oglart.loopstation;

import javax.sound.sampled.AudioFormat;

public interface AudioRecorder {
    AudioRecorderResult record();

    class AudioRecorderResult {
        private byte[] content;
        private AudioFormat format;

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public AudioFormat getFormat() {
            return format;
        }

        public void setFormat(AudioFormat format) {
            this.format = format;
        }
    }
}
