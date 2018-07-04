package sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/* Classname: Sound
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 04.07.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class Sound {
	
	// method for sound playing
		public static void playSound(String soundName)
		 {
		   try 
		   {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
		    Clip clip = AudioSystem.getClip( );
		    clip.open(audioInputStream);
		    clip.start( );
		   }
		   catch(Exception ex)
		   {
		     System.out.println("Error with playing sound.");
		     ex.printStackTrace( );
		   }
		 }

}
