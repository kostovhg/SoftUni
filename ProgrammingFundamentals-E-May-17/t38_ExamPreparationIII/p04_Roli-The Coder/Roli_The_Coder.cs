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

            public void AddGuest(string guestNames)
            {
                MatchCollection mc = Regex.Matches(guestNames, @"(@\S+)");
                if (mc.Count < 1) return;
                foreach (Match m in mc)
                {
                    this.Guests.Add(m.Groups[1].Value);
                }
            }

            public static Event Parse(string input)
            {

                Match m = new Regex(@"(?'id'[0-9]+) #(?'event'\S+)(?'names'( @\S+)+)?").Match(input);
                if (!m.Success) return null;
                var instance = new Event();
                instance.ID = long.Parse(m.Groups["id"].Value);
                instance.EventName = m.Groups["event"].Value;
                instance.Guests = new SortedSet<string>
                    (from string name in m.Groups["names"].Value
                     .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
                     .ToArray()
                     select name);

                return instance;
            }
        }
        static void Main(string[] args)
        {
            Dictionary<long, Event> events = new Dictionary<long, Event>();
            string input;
            while (!"Time for Code".Equals(input = Console.ReadLine()))
            {
                Event e = Event.Parse(input);
                if (e == null) continue;
                if (events.ContainsKey(e.ID))
                {
                    if (events[e.ID].EventName.Equals(e.EventName))
                    {
                        events[e.ID].AddGuest(input);
                    }
                }
                else
                {
                    events.Add(e.ID, e);
                }
            }
            foreach (Event e in events.Values
                .OrderByDescending(e => e.Guests.Count)
                    .ThenBy(e => e.EventName))
            {
                Console.WriteLine($"{e.EventName} - {e.Guests.Count}");
                foreach (string guest in e.Guests)
                {
                    Console.WriteLine(guest);
                }
            }
        }
    }
}
