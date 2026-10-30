package com.example.stormstaff.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Right-click to call a lightning bolt down on whatever block or spot you are looking at.
 * Each strike costs one durability and puts the staff on cooldown.
 */
public class StormStaffItem extends Item {
    public static final int MAX_DURABILITY = 64;
    public static final double RANGE = 48.0;
    public static final int COOLDOWN_TICKS = 40;

    public StormStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        HitResult hit = user.raycast(RANGE, 0.0F, false);
        if (hit.getType() == HitResult.Type.MISS) {
            // Nothing in range: fizzle with a sound but don't spend durability.
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 1.5F);
            return TypedActionResult.fail(stack);
        }

        if (world instanceof ServerWorld serverWorld) {
            LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(serverWorld);
            if (bolt != null) {
                Vec3d pos = hit.getPos();
                bolt.refreshPositionAfterTeleport(pos.x, pos.y, pos.z);
                if (user instanceof ServerPlayerEntity serverPlayer) {
                    bolt.setChanneler(serverPlayer);
                }
                serverWorld.spawnEntity(bolt);
            }

            stack.damage(1, user, LivingEntity.getSlotForHand(hand));
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.PLAYERS, 0.6F, 1.2F);

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.stormstaff.storm_staff.tooltip").formatted(Formatting.AQUA));
        tooltip.add(Text.translatable("item.stormstaff.storm_staff.tooltip.range", (int) RANGE)
                .formatted(Formatting.GRAY));
    }
}
