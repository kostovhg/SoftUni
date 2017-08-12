using System;
using System.Collections.Generic;

namespace p05_UserLogins
{
    class UserLogins
    {
        static void Main(string[] args)
        {
            Dictionary<string, string> logins = new Dictionary<string, string>();
            uint fails = 0;
            string input = Console.ReadLine();
            while (!input.Equals("login"))
            {
                string[] tokens = input.Split();
                string name = tokens[0];
                string pass = tokens[2];
                if (!logins.ContainsKey(name))
                {
                    logins.Add(name, pass);
                }
                else
                {
                    logins[name] = pass;
                }
                input = Console.ReadLine();
            }
            input = Console.ReadLine();
            while (!input.Equals("end"))
            {
                string[] tokens = input.Split();
                string name = tokens[0];
                string pass = tokens[2];
                if (logins.ContainsKey(name) && logins[name] == pass)
                {
                    Console.WriteLine($"{name}: logged in successfully");
                }
                else
                {
                    Console.WriteLine($"{name}: login failed");
                    fails++;
                }
                input = Console.ReadLine();
            }
            Console.WriteLine($"unsuccessful login attempts: {fails}");
        }
    }
}
