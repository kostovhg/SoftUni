using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04_JapaneseRoulette
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] cylinder = Console.ReadLine()
                .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
            string[] players = Console.ReadLine()
                .Split();
            byte place = 0;
            for (int i = 0; i < 6; i++)
            {
                if (cylinder[i].Equals("1"))
                {
                    place = (byte)i;
                }
            }
            for (int i = 0; i < players.Length; i++)
            {
                int power = int.Parse(players[i].Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries)[0]);
                string direction = players[i].Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries)[1];
                if (direction[0].Equals('L'))
                {
                    place = (byte)(5 - Math.Abs(power - place -1) % 6);
                }
                else
                {
                    place = (byte)((power + place) % 6);
                }
                if (place == 2)
                {
                    Console.WriteLine("Game over! Player {0} is dead.", i);
                    return;
                }
                place = (byte)((place + 1) % 6);
            }
            Console.WriteLine("Everybody got lucky!");
        }
    }
}
