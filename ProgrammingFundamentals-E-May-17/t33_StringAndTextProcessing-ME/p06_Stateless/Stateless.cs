using System;
using System.Text;

namespace p06_Stateless
{
    class Stateless
    {
        static void Main(string[] args)
        {
            StringBuilder state = new StringBuilder(Console.ReadLine());
            while (!"collapse".Equals(state.ToString()))
            {
                StringBuilder fiction = new StringBuilder(Console.ReadLine());
                Collapse(state, fiction);
                Console.WriteLine((state.Length > 0) ? state.ToString().Trim(' ') : "(void)");
                state.Clear();
                state.Append(Console.ReadLine());
            }

        }

        private static void Collapse(StringBuilder state, StringBuilder fiction)
        {
            while (fiction.Length > 0)
            {
                state.Replace(fiction.ToString(), "");
                fiction.Remove(0, 1);
                if (fiction.Length > 0) fiction.Length--;
                Collapse(state, fiction);
            }
            return;
        }
    }
}
