package net.picklestring.flux_casting.models.item;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.resource.Material;
import net.minecraft.client.texture.*;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class RingModel implements UnbakedModel, BakedModel, FabricBakedModel {
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, RandomGenerator random) {
		return null;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean hasDepth() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return false;
	}

	@Override
	public boolean isBuiltin() {
		return false;
	}

	@Override
	public Sprite getParticleSprite() {
		return null;
	}

	@Override
	public ModelTransformation getTransformation() {
		return null;
	}

	@Override
	public ModelOverrideList getOverrides() {
		return null;
	}

	@Override
	public Collection<Identifier> getModelDependencies() {
		return List.of();
	}

	@Override
	public void resolveParents(Function<Identifier, UnbakedModel> models) {

	}

	@Nullable
	@Override
	public BakedModel bake(ModelBaker modelBaker, Function<Material, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
		return this;
	}

	@Override
	public void emitItemQuads(ItemStack itemStack, Supplier<RandomGenerator> supplier, RenderContext renderContext) {
		/*Mesh mesh;
		Sprite sprite = SpriteAtlas

		// Build the mesh using the Renderer API
		Renderer renderer = RendererAccess.INSTANCE.getRenderer();
		MeshBuilder builder = renderer.meshBuilder();
		QuadEmitter emitter = renderContext.getEmitter();

		for(Direction direction : Direction.values()) {
			// UP and DOWN share the Y axis
			int spriteIdx = direction == Direction.UP || direction == Direction.DOWN ? SPRITE_TOP : SPRITE_SIDE;
			// Add a new face to the mesh
			emitter.square(direction, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
			// Set the sprite of the face, must be called after .square()
			// We haven't specified any UV coordinates, so we want to use the whole texture. BAKE_LOCK_UV does exactly that.
			emitter.spriteBake(sprites[spriteIdx], MutableQuadView.BAKE_LOCK_UV);
			// Enable texture usage
			emitter.color(-1, -1, -1, -1);
			// Add the quad to the mesh
			emitter.emit();
		}
		mesh = builder.build();

		mesh.outputTo(renderContext.getEmitter());*/
	}
}
