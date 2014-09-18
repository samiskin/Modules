using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Windows.Forms;
using System.Media;

// 
// Application Name: Drunk PC
// Description: Application that generates erratic mouse and keyboard movements and input generates system sounds and fake dialogs to confuse the user
// Topics 
//  1) Threads
//  2) System.Windows.Forms namespace and assembly
//  3) Hidden application

namespace DrunkPC
{
    class Program
    {
        public static Random _random = new Random(); // underscore is for global variables
        public static int _startupDelaySeconds = 0;
        public static int _totalDurationSeconds = 100;


        /// <summary>
        /// Entry point for prank application
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            Console.WriteLine("DrunkPC Prank Application by Jerry");

            if (args.Length > 2)
            {
                _startupDelaySeconds = Convert.ToInt32(args[0]);
                _totalDurationSeconds = Convert.ToInt32(args[1]);
            }


            // Create all threads
            Thread drunkMouseThread = new Thread(new ThreadStart(DrunkMouseThread));
            Thread drunkKeyboardThread = new Thread(new ThreadStart(DrunkKeyboardThread));
            Thread drunkSoundThread = new Thread(new ThreadStart(DrunkSoundThread));
            Thread drunkPopupThread = new Thread(new ThreadStart(DrunkPopupThread));


            DateTime future = DateTime.Now.AddSeconds(_startupDelaySeconds);
            while (future > DateTime.Now)
            {
                Thread.Sleep(1000);
            }


            // Start all threads
            drunkMouseThread.Start();
            drunkKeyboardThread.Start();
            drunkSoundThread.Start();
            //drunkPopupThread.Start();

            future = DateTime.Now.AddSeconds(_totalDurationSeconds);
            while (future > DateTime.Now)
            {
                Thread.Sleep(1000);
            }

            // Kill all threads and exit
            drunkMouseThread.Abort();
            drunkKeyboardThread.Abort();
            drunkSoundThread.Abort();
            drunkPopupThread.Abort();

        }



        #region Thread Functions
        /// <summary>
        /// This thread will randomly affecty the mouse movements to crew with the end user
        /// </summary>
        public static void DrunkMouseThread()
        {
            Console.WriteLine("DrunkMouseThread Started");

            int moveX = 0;
            int moveY = 0;
            int magnitude = 5;

            while (true)
            {
                moveX = _random.Next(magnitude*2) - magnitude;
                moveY = _random.Next(magnitude*2) - magnitude;

                Cursor.Position = new System.Drawing.Point(Cursor.Position.X - moveX, Cursor.Position.Y - moveY);
                Thread.Sleep(500);
            }
        }


        public static void DrunkKeyboardThread()
        {
            Console.WriteLine("DrunkKeyboardThread Started");
            while (true)
            {
                if (_random.Next(100) > 80)
                {
                    char key = (char)(_random.Next(25) + 65);

                    if (_random.Next(2) == 0)
                    {
                        key = Char.ToLower(key);
                    }


                    SendKeys.SendWait(key.ToString());
                    Thread.Sleep(_random.Next(500));

                }
            }
        }

        public static void DrunkSoundThread()
        {
            Console.WriteLine("DrunkSoundThread Started");
            while (true)
            {
                if (_random.Next(100) > 80)
                {
                    switch (_random.Next(5))
                    {
                        case 0:
                            SystemSounds.Asterisk.Play();
                            break;
                        case 1:
                            SystemSounds.Beep.Play();
                            break;
                        case 2:
                            SystemSounds.Exclamation.Play();
                            break;
                        case 3:
                            SystemSounds.Hand.Play();
                            break;
                        case 4:
                            SystemSounds.Question.Play();
                            break;

                    }
                }
                Thread.Sleep(100);
            }
        }
                 
        public static void DrunkPopupThread()
        {
            Console.WriteLine("DrunkPopupThread Started");
            while (true)
            {
                MessageBox.Show("Google Chrome has stopped working", "Google Chrome", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Thread.Sleep(500);

            }
        }

        #endregion

    }
}
