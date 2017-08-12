using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;

namespace p04_HornetArmada
{
    class HornetArmada
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            Dictionary<string, ulong> activities = new Dictionary<string, ulong>();
            Dictionary<string, Dictionary<string, ulong>> legions = new Dictionary<string, Dictionary<string, ulong>>();
            Regex inPattern = new Regex(@"^(?'last'[0-9]+) = (?'legion'[^=->:]+) -> (?'soldier'[^=->:]+):(?'count'[0-9]+)$");
            for (int i = 0; i < n; i++)
            {
                Match m = inPattern.Match(Console.ReadLine());
                string lName = m.Groups["legion"].Value;
                string sType = m.Groups["soldier"].Value;
                uint sCount = uint.Parse(m.Groups["count"].Value);
                uint lastActivity = uint.Parse(m.Groups["last"].Value);
                // check if we have the legion
                if (!legions.ContainsKey(lName))
                {
                    legions.Add(lName, new Dictionary<string, ulong>());
                    activities.Add(lName, lastActivity);
                }
                // check if we have this type of soldiers
                if (!legions[lName].ContainsKey(sType))
                {
                    legions[lName][sType] = 0;
                }
                if (activities[lName] < lastActivity)
                {
                    activities[lName] = lastActivity;
                }

                legions[lName][sType] += sCount;
            }

            PrintLegions(Console.ReadLine(), legions, activities);
        }

        private static void PrintLegions(string v, Dictionary<string, Dictionary<string, ulong>> legions, Dictionary<string, ulong> activities)
        {
            string[] parameters = v.Split('\\');
            if (parameters.Length < 2)
            {
                string sType = parameters[0];
                foreach (KeyValuePair<string, ulong> legion in activities.OrderByDescending(l => l.Value))
                {
                    if (legions[legion.Key].ContainsKey(sType))
                    {
                        Console.WriteLine("{0} : {1}", legion.Value, legion.Key);
                    }
                }
            }
            else
            {
                string sType = parameters[1];
                ulong cActivity = ulong.Parse(parameters[0]);
                foreach (var legion in legions
                    .Where(l => l.Value.ContainsKey(sType))
                    .OrderByDescending(x => x.Value[sType])
                    )
                {
                    if (activities[legion.Key] < cActivity)
                    {
                        Console.WriteLine("{0} -> {1}", legion.Key, legion.Value[sType]);
                    }

                }
            }
        }
    }
}
