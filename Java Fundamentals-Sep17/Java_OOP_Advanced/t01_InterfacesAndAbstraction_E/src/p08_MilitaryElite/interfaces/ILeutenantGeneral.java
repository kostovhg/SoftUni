package p08_MilitaryElite.interfaces;

import java.util.Collection;

public interface ILeutenantGeneral extends IPrivate{

    Collection<IPrivate> getPrivates();

    @Override
    String toString();
}
