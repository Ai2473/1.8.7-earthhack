package me.earth.earthhack.impl.modules.misc.suffix;

import me.earth.earthhack.api.module.data.DefaultData;

final class SuffixData extends DefaultData<Suffix>
{
    public SuffixData(Suffix module)
    {
        super(module);
        this.descriptions.put(module.mode,
                "Choose the suffix you want to use.");
    }

    @Override
    public int getColor()
    {
        return 0xffaa001D;
    }

    @Override
    public String getDescription()
    {
        return "Add a suffix to your messages.";
    }

}
