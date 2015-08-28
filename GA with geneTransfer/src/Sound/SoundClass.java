/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sound;
import java.io.*;
import sun.audio.*;

/**
 * A simple Java sound file(i.e., Java code to play a sound file)
 * @author Hasib
 */
 
public class SoundClass
{
  public static void sound() throws FileNotFoundException, IOException
  {
    // open the sound file as a Java input stream
    String gongFile = "./src/Sound/soundClip2.au";
    InputStream in = new FileInputStream(gongFile);
 
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
 
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
}