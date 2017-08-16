using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p04_Roli_The_Coder
{
    class Event
    {
        public string ID { get; set; }
        public string Name { get; set; }
        public SortedSet<string> Guests { get; set; }

    }   
    class Roli_The_Coder
    {
        static void Main()
        {
            List<Event> events = new List<Event>();
            string input;
            while (!"Time for Code".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                if (!tokens[1].StartsWith("#") 
                    || events.Any(x => x.ID.Equals(tokens[0]) 
                        && !x.Name.Equals(tokens[1].TrimStart('#'))))
                {
                    continue;
                }
                string id = tokens[0];
                string name = tokens[1].TrimStart('#');
                string[] guests = tokens.Skip(2).ToArray();
                if (events.Any(x => x.ID.Equals(tokens[0])))
                {
                    Event ev = events.FirstOrDefault(e => e.ID.Equals(id));
                    foreach (string guest in guests)
                    {
                        ev.Guests.Add(guest);
                    }
                }
                else
                {
                    events.Add(new Event() { ID = id, Name = name, Guests = new SortedSet<string>(guests) });
                }
            }

            foreach (Event e in events
                .OrderByDescending(x => x.Guests.Count))
            {
                Console.WriteLine($"{e.Name} - {e.Guests.Count}");
                foreach (string guest in e.Guests)
                {
                    Console.WriteLine($"{guest}");
                }
            }
        }
    }
}
