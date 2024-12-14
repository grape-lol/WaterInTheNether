package fuck.actual.the.what.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin {
	@Inject(method = "placeFluid", at = @At("HEAD"), cancellable = true)
	private void injectPlaceFluid(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir) {
		ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
		if (itemStack.getItem() == Items.WATER_BUCKET) {
			if (world.getDimension().ultrawarm()) {
				BlockState blockState = world.getBlockState(pos);
				if (blockState.isAir() || blockState.isReplaceable()) {
					world.setBlockState(pos, Blocks.WATER.getDefaultState(), 11);
					if (!player.getAbilities().creativeMode) {
						itemStack.decrement(1);
						player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));
					}
					cir.setReturnValue(true);
				} else {
					cir.setReturnValue(false);
				}
			}
		}
	}
}