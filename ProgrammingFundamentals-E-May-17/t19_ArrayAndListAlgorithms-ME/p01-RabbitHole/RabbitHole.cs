using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_RabbitHole
{
    class RabbitHole
    {
        static void Main(string[] args)
        { 
            List<string> input = Console.ReadLine().Split().ToList();
            int energy = int.Parse(Console.ReadLine());
            int index = 0;
            while (energy > 0)
            {
                if (input[index].Equals("RabbitHole"))
                {
                    Console.WriteLine("You have 5 years to save Kennedy!");
                    break;
                }
                int indexChange = int.Parse(input[index].Split('|')[1]);
                string direction = input[index].Split('|')[0];
                bool isDeadFromBomb = false;
                switch (direction)
                {
                    case "Left":
                        index = Math.Abs(index - indexChange) % input.Count; break;
                    case "Right": index = (index + indexChange) % input.Count; break;
                    case "Bomb": input.RemoveAt(index); index = 0; isDeadFromBomb = true; break;
                }
                //if (!direction.Equals("Bomb"))
                //{
                //    index = (direction.Equals("Right")) ?
                //        (index + indexChange) % (input.Count) :
                //        Math.Abs(index - indexChange) % (input.Count);
                //        // input.Count - 1 - (indexChange - index -1) % input.Count;
                //}
                //else
                //{
                //    if (energy - indexChange < 0)
                //    {
                //        Console.WriteLine("You are dead due to bomb explosion!");
                //        return;
                //    }
                //    input.RemoveAt(index);
                //    index = 0;
                //}
                energy -= indexChange;
                //if (energy <= 0)
                //{
                //    Console.WriteLine("You are tired. You can't continue the mission.");
                //    return;
                //}
                if (energy <= 0 && isDeadFromBomb)
                {
                    Console.WriteLine("You are dead due to bomb explosion!");
                }
                else if (energy <= 0 && !isDeadFromBomb)
                {
                    Console.WriteLine("You are tired. You can't continue the mission.");
                }

                if (!input[input.Count -1].Equals("RabbitHole"))
                {
                    input.RemoveAt(input.Count - 1);
                }
                input.Add("Bomb|" + energy);
            }
        }
    }
}
