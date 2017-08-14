using System;
using System.Linq;

namespace p02_Ladybugs
{
    class Ladybugs
    {
        static void Main(string[] args)
        {
            int fieldSize = int.Parse(Console.ReadLine());
            bool[] ladybugs = new bool[fieldSize];
            int[] possitions = Console.ReadLine().Split().Select(int.Parse).ToArray();
            foreach (int index in possitions)
            {
                // if the index in field doesn't existed, skip
                if (index >= fieldSize || index < 0) continue;
                ladybugs[index] = true;
            }
            string[] command;
            while (!"end".Equals((command = Console.ReadLine().Split())[0]))
            {
                int start = int.Parse(command[0]);
                // check if start is valid, and if it is, do we have a ladybug on "start" index
                if (start < 0 || start >= ladybugs.Length || !ladybugs[start]) continue;
                int jump = int.Parse(command[2]);
                // invert jump sign if we have "left"
                if (!"right".Equals(command[1])) jump = -jump;
                // remove ladybug from start position
                ladybugs[start] = false;
                // find jump destination, if it is in the field, try to jump
                // if the destination is in the field, but ocupied, increase/decrease the
                // destination index with another jump
                for (int i = start + jump; i < ladybugs.Length && i > -1; i += jump)
                {
                    // if jump destination is ocupied, skip
                    if (ladybugs[i]) continue;
                    // else put the ladybug there, and finish the loop
                    ladybugs[i] = true;
                    break;
                }
            }
            // Print the array, converting boolean to byte (0, 1)
            Console.WriteLine(string.Join(" ", ladybugs.Select(x => Convert.ToByte(x))));
        }
    }
}
 