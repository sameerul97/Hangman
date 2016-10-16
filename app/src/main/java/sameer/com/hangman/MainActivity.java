package sameer.com.hangman;

import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String words[] = {"ant","baboon","badger","bat","bear","beaver","camel","cat","clam","cobra","cougar","coyote","crow","deer","dog","donkey","duck","eagle","ferret","fox","frog","goat","goose","hawk","lion","lizard","llama","mole","monkey","moose","mouse","mule","newt","otter","owl","panda","parrot","pigeon","python","rabbit","ram","rat","raven","rhino","salmon","seal","shark","sheep","skunk","sloth","snake","spider","stork","swan","tiger","toad","trout","turkey","turtle","weasel","whale","wolf","wombat","zebra"};
    Random random = new Random();
    int rand = random.nextInt(words.length);
    TextView secretword;
    String correctLetter =  "";
    String inCorrectLetter = "";
    String temp;
    int hangmanIndex = 0;
    //
    public void checkUserInput(View view){

        EditText userinput = (EditText)findViewById(R.id.userinput); // Gets theuser input
        System.out.println(userinput);
        String temp = userinput.getText().toString(); // This is the userinput stored in a variable
        if (userinput.length() > 1){
            System.out.println("Has to be one character");
            Toast.makeText(this,"Has to be one character " + temp ,Toast.LENGTH_SHORT).show();
        }
        else if (userinput.length() <= 0){
            System.out.println("Enter a character");
            Toast.makeText(this,"Cant be empty" + temp ,Toast.LENGTH_SHORT).show();
        }
        else if (correctLetter.contains(temp)){
            System.out.println("Already Guessed that letter");
            Toast.makeText(this,"Already Guessed that letter ",Toast.LENGTH_SHORT).show();
        }

        else{
            System.out.println("Good Calling on click function");
            onClick(temp);
        }


    }
    public void onClick(String temp){
        TextView secretword = (TextView)findViewById(R.id.secretword); //Shows the scret word to the user
        String secretwordinstring = secretword.getText().toString(); //
        int index = 0;
        boolean ans = false ;
        System.out.println("Correct Letters " + correctLetter);
        System.out.println("Incorrect Letters " + inCorrectLetter);
        while (index < secretword.length())
        {
            char ch1 = secretwordinstring.charAt(index);
            char ch2 = temp.charAt(0);
            if (ch1 == ch2)
            {
                correctLetter = correctLetter + temp;
                Toast.makeText(this,"Found letter ",Toast.LENGTH_SHORT).show();
                System.out.println("Found letter ");
                ans = true;
            }
            else
            {
            }
            index++;
        }
        ImageView  imageView = (ImageView)findViewById(R.id.imageView);
        int[] Pics = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7};

        if (ans == false)
        {
            inCorrectLetter = inCorrectLetter + temp;
            hangmanIndex ++;
            imageView.setImageResource(Pics[hangmanIndex]);
        }

        if (correctLetter.length() == secretword.length())
        {

            LinearLayout linearLayoutplayagain = (LinearLayout) findViewById(R.id.playagainlayout); // shows the win or lost and playagain to the user
            linearLayoutplayagain.setVisibility(linearLayoutplayagain.VISIBLE);
            TextView winnermessage = (TextView)findViewById(R.id.winnerMessage);
            winnermessage.setText("  You Won  ");


        }
        TextView correctLetters = (TextView)findViewById(R.id.correctLetter);
        correctLetters.setText(correctLetter);
        TextView inCorrectLetters = (TextView)findViewById(R.id.inCorrectLetter);
        inCorrectLetters.setText(inCorrectLetter);

        if  (hangmanIndex == 6){
            Toast.makeText(this," You Lost Mate ",Toast.LENGTH_SHORT).show();
            LinearLayout linearLayoutplayagain = (LinearLayout) findViewById(R.id.playagainlayout); // shows the win or lost and playagain to the user
            linearLayoutplayagain.setVisibility(linearLayoutplayagain.VISIBLE);
            TextView winnermessage = (TextView)findViewById(R.id.winnerMessage);
            winnermessage.setText("  You Lost  ");
        }

    }

    public void playAgain(View view){
        //Button playAgain= (Button)findViewById(R.id.playAgain);
        LinearLayout linearLayoutplayagain = (LinearLayout) findViewById(R.id.playagainlayout); // shows the win or lost and playagain to the user
        linearLayoutplayagain.setVisibility(linearLayoutplayagain.INVISIBLE);
        int rand = random.nextInt(words.length);
        TextView secretword = (TextView)findViewById(R.id.secretword);
        secretword.setText(words[rand]);
        correctLetter =  "";
        inCorrectLetter = "";
        temp = "";
        hangmanIndex=0;
        ImageView  imageView = (ImageView)findViewById(R.id.imageView);
        int[] Pics = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7};
        imageView.setImageResource(Pics[hangmanIndex]);
        TextView correctLetters = (TextView)findViewById(R.id.correctLetter);
        correctLetters.setText(correctLetter);
        TextView inCorrectLetters = (TextView)findViewById(R.id.inCorrectLetter);
        inCorrectLetters.setText(inCorrectLetter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView secretword = (TextView)findViewById(R.id.secretword); //Shows the scret word to the user
        secretword.setText(words[rand]);
        String secretwordinstring = secretword.getText().toString();
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        String a= secretwordinstring.replaceAll(".","_ ");
        textView2.setText(a);
        TextView correctLetters = (TextView)findViewById(R.id.correctLetter);
        correctLetters.setText(correctLetter);
        TextView inCorrectLetters = (TextView)findViewById(R.id.inCorrectLetter);
        inCorrectLetters.setText(inCorrectLetter);
        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playagainlayout); // Shows layout after user lost
        playAgain.setVisibility(playAgain.INVISIBLE);

        //System.out.println("sameer".replaceAll(".","_ "));



    }
}
