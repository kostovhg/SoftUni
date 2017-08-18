using System;

namespace p05_BPMCounter
{
    class BPMCounter
    {
        static void Main(string[] args)
        {
            int bpm = int.Parse(Console.ReadLine());
            int number = int.Parse(Console.ReadLine());
            double bars = Math.Round(number / 4.0, 1);
            int seconds = number*60 / bpm;
            int min = seconds / 60;
            seconds = seconds % 60;
            
            Console.WriteLine($"{bars} bars - {min}m {seconds}s");
        }
    }
}
