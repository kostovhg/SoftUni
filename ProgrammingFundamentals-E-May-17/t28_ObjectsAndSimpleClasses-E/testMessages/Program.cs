using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace testMessages
{
    class A
    {
        public string Name { get; set; }
        public List<B> List { get; set; }

        public void AddToList(string msg, string sen)
        {
            this.List.Add(new B(msg, sen);
        }
    }

    class B
    {
        string Content { get; set; }
        A Source { get; set; }
        public B(string cont, A sender)
        {
            this.Content = cont;
            this.Source = sender;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
        }
    }
}
