package net.glowstone.block.state;

import net.glowstone.block.GlowBlock;
import net.glowstone.block.GlowBlockState;
import net.glowstone.block.ItemTable;
import net.glowstone.block.blocktype.BlockChest;
import net.glowstone.block.entity.TEChest;
import net.glowstone.inventory.GlowDoubleChestInventory;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

public class GlowChest extends GlowBlockState implements Chest {

    public GlowChest(GlowBlock block) {
        super(block);
    }

    private TEChest getTileEntity() {
        return (TEChest) getBlock().getTileEntity();
    }

    @Override
    public Inventory getBlockInventory() {
        return getTileEntity().getInventory();
    }

    @Override
    public Inventory getInventory() {
        GlowBlock me = getBlock();
        BlockChest blockChest = (BlockChest) ItemTable.instance().getBlock(me.getType());
        BlockFace attachedChest = blockChest.getAttachedChest(me);

        if (attachedChest != null) {
            Block nearbyBlock = me.getRelative(attachedChest);
            GlowChest nearbyChest = (GlowChest) nearbyBlock.getState();
            return new GlowDoubleChestInventory(this, nearbyChest);
        }

        return getBlockInventory();
    }
}
