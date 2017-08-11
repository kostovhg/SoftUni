using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;

namespace p01_RegisteredUsers
{
    class RegisteredUsers
    {
        static void Main(string[] args)
        {
            Dictionary<string, DateTime> users = new Dictionary<string, DateTime>();

            string input = Console.ReadLine();
            while (!"end".Equals(input))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                string user = tokens[0];
                DateTime date = DateTime.ParseExact(tokens[1], "dd/MM/yyyy", CultureInfo.InvariantCulture);

                users.Add(user, date);

                input = Console.ReadLine();
            }

            foreach (KeyValuePair<string, DateTime> kvp in users
                .Reverse()
                .OrderBy(x => x.Value)
                .Take(5)
                .OrderByDescending(y => y.Value)
                )
            {
                Console.WriteLine(kvp.Key);
            }
        }
    }
}
