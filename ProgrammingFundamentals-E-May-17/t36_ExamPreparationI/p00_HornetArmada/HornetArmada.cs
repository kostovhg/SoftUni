using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;

namespace p04_HornetArmada
{
    class HornetLegion
    {
        public string Name { get; set; }
        public List<HornetSoldier> SoldiersTypes { get; set; }
        public uint LastActivity { get; set; }

        public bool HasSoldier(string soldierType)
        {
            if (this.SoldiersTypes == null) return false;
            bool has = false;
            foreach (var item in this.SoldiersTypes)
            {
                if (has = item.Name.Equals(soldierType)) return has;
            }
            return has;
        }
        public void AddCount(string soldierType, uint count)
        {
            for (int i = 0; i < this.SoldiersTypes.Count; i++)
            {
                if (SoldiersTypes[i].Name.Equals(soldierType))
                {
                    SoldiersTypes[i].Count += count;
                    break;
                }
            }
        }

        internal void UpdateActivity(uint lastActivity)
        {
            if (this.LastActivity < lastActivity)
            {
                this.LastActivity = lastActivity;
            }
        }

        public uint SoldiersCount()
        {
            uint sum = 0;
            foreach (var type in this.SoldiersTypes)
            {
                sum += type.Count;
            }
            return sum;
        }

        internal uint SoldiersCount(string v)
        {
            uint sum = 0;
            foreach (var type in this.SoldiersTypes
                .Where(x => x.Name.Equals(v)))
            {
                sum += type.Count;
            }
            return sum;
        }
    }

    class HornetSoldier
    {
        public string Name { get; set; }
        public uint Count { get; set; }
    }

    class HornetArmada
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            Dictionary<string, HornetLegion> legions = new Dictionary<string, HornetLegion>();
            Regex inPattern = new Regex(@"^(?'last'[0-9]+) = (?'legion'[^=->:]+) -> (?'soldier'[^=->:]+):(?'count'[0-9]+)$");
            for (int i = 0; i < n; i++)
            {
                Match m = inPattern.Match(Console.ReadLine());
                string lName = m.Groups["legion"].Value;
                string sType = m.Groups["soldier"].Value;
                uint sCount = uint.Parse(m.Groups["count"].Value);
                uint lastActivity = uint.Parse(m.Groups["last"].Value);
                // create new legion with name and last acitivty = 0
                if (!legions.ContainsKey(lName))
                    legions.Add(lName, new HornetLegion()
                    { LastActivity = 0, Name = lName, SoldiersTypes = new List<HornetSoldier>() });
                // update activity if it is bigger than existing
                legions[lName].UpdateActivity(lastActivity);
                // if new type of soldiers is not pressent in the legion add it
                // else correct the count with addition!? the problem explonations
                // are not very clear
                if (!legions[lName].HasSoldier(sType))
                {
                    HornetSoldier cSoldier = new HornetSoldier();
                    cSoldier.Name = sType;
                    cSoldier.Count = sCount;
                    legions[lName].SoldiersTypes.Add(cSoldier);
                }
                else
                    legions[lName].AddCount(sType, sCount);
            }

            PrintLegions(Console.ReadLine(), legions);
        }

        private static void PrintLegions(string v, Dictionary<string, HornetLegion> legions)
        {
            string[] parameters = v.Split('\\');
            if (parameters.Length < 2)
            {
                foreach (HornetLegion legion in legions.Values.
                    Where(x => x.HasSoldier(parameters[0]))
                    .OrderByDescending(x => x.LastActivity))
                {
                    Console.WriteLine("{0} : {1}", legion.LastActivity, legion.Name);
                }
            }
            else
            {
                foreach (HornetLegion legion in legions.Values
                    .Where(x => x.LastActivity < uint.Parse(parameters[0]))
                    .Where(x => x.HasSoldier(parameters[1]))
                    .OrderByDescending(x => x.SoldiersCount()))
                {
                    Console.WriteLine("{0} -> {1}", legion.Name, legion.SoldiersCount(parameters[1]));
                }
            }
        }
    }
}
