using System;
using System.Text;

namespace p05_StringCommander
{
    class StringCommander
    {
        static void Main(string[] args)
        {
            StringBuilder theString = new StringBuilder(Console.ReadLine());
            string input;
            while (!"end".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(' ');
                switch(tokens[0])
                {
                    case "Left":
                        MoveLeft(theString, int.Parse(tokens[1]), true);
                        break;
                    case "Right":
                        MoveLeft(theString, int.Parse(tokens[1]), false);
                        break;
                    case "Insert":
                        theString.Insert(int.Parse(tokens[1]), tokens[2]);
                        break;
                    case "Delete":
                        int startIndex = int.Parse(tokens[1]);
                        int endIndex = int.Parse(tokens[2]);
                        theString.Remove(startIndex, endIndex - startIndex + 1);
                             break;
                }
            }
            Console.WriteLine(theString);
        }

        private static void MoveLeft(StringBuilder theString, int v, bool left)
        {
            int possitions = v % theString.Length;
            if (!left) possitions = theString.Length - possitions;
            string tmp = theString.ToString().Substring(0, possitions);
            theString.Remove(0, possitions).Append(tmp);
             
        }
    }
}
