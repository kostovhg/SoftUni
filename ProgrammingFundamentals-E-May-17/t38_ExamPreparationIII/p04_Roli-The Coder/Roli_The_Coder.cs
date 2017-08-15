using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p04_Roli_The_Coder
{
    class Roli_The_Coder
    {
        class Event
        {
            public long ID { get; set; }
            public string EventName { get; set; }
            public SortedSet<string> Guests { get; set; }
            public void AddNames(string names)
            {
                if (String.IsNullOrEmpty(names)) return;
                foreach (string name in names.Split(new char[] { ' '}, StringSplitOptions.RemoveEmptyEntries))
                {
                    this.Guests.Add(name);
                }
            }

            public Event(string input)
            {
                Match m = new Regex(@"^(?'id'[0-9]+)(?:\s+#)(?'event'\S+)\s?(?'names'(?:@\S*\s*){1,100})?$")
                    .Match(input);
                if (!m.Success) return;
                this.Guests = new SortedSet<string>();
                this.AddNames(m.Groups["names"].Value);
                this.ID = long.Parse(m.Groups["id"].Value);
                this.EventName = m.Groups["event"].Value;
            }

        }
        static void Main(string[] args)
        {
            Dictionary<long, Event> events = new Dictionary<long, Event>();
            string input;
            while (!"Time for Code".Equals(input = Console.ReadLine()))
            {
                Event e = new Event(input);
                if (e.EventName == null) continue;
                long id = e.ID;
                string name = e.EventName;
                SortedSet<string> guests = e.Guests;
                if (!events.ContainsKey(id))
                {
                    events[id] = e;
                }
                else if(events[id].EventName.Equals(name))
                {
                    events[id].Guests.UnionWith(e.Guests);
                }
            }
            foreach (Event e in events.Values
                .OrderByDescending(e => e.Guests.Count))
            {
                Console.WriteLine($"{e.EventName} - {e.Guests.Count}");
                foreach (string guest in e.Guests)
                {
                    Console.WriteLine($"{guest}");
                }
            }
        }
    }
}
