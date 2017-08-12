using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace p02_HornetComm
{
    class HornetComm
    {
        static void Main(string[] args)
        {
            string input;
            Regex privatePattern = new Regex(@"^(?'recipient'[0-9]+) <-> (?'msg'[a-zA-Z0-9]+)$");
            Regex broadcastPattern = new Regex(@"^(?'msg'[^0-9]+) <-> (?'freq'[a-zA-Z0-9]+)$");
            List<string[]> broadcasts = new List<string[]>();
            List<string[]> messages = new List<string[]>();
            while (!"Hornet is Green".Equals(input = Console.ReadLine()))
            {
                bool privateIn = privatePattern.IsMatch(input);
                bool broadcastIn = broadcastPattern.IsMatch(input);
                if (!privateIn && !broadcastIn) continue;
                if (privateIn)
                {
                    Match m = privatePattern.Match(input);
                    messages.Add(new string[]
                    {
                        ReverseString(m.Groups["recipient"].Value),
                        m.Groups["msg"].Value
                    });
                }
                else
                {
                    Match m = broadcastPattern.Match(input);
                    broadcasts.Add(new string[] 
                    {
                        InvertCase(m.Groups["freq"].Value),
                        m.Groups["msg"].Value
                    });
                }

            }
            Console.WriteLine("Broadcasts:");
            foreach (string[] msg in broadcasts)
            {
                Console.WriteLine("{0} -> {1}", msg[0], msg[1]);
            }
            if (broadcasts.Count < 1) Console.WriteLine("None");
            Console.WriteLine("Messages:");
            foreach (string[] msg in messages)
            {
                Console.WriteLine("{0} -> {1}", msg[0], msg[1]);
            }
            if (messages.Count < 1) Console.WriteLine("None");
        }

        private static string InvertCase(string value)
        {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < value.Length; i++)
            {
                if (value[i] > 64 && value[i] < 91)
                {
                    output.Append(value[i].ToString().ToLower());
                }
                else
                {
                    output.Append(value[i].ToString().ToUpper());
                }
            }
            return output.ToString();
        }
        public static string ReverseString(string s)
        {
            char[] array = s.ToCharArray();
            Array.Reverse(array);
            return new string(array);
        }
    }
}
