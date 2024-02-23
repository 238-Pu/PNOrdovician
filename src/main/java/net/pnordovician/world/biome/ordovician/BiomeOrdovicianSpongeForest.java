
package net.pnordovician.world.biome.ordovician;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockCoral;
import net.lepidodendron.block.BlockStromatoporoideaReef;
import net.lepidodendron.util.EnumBiomeTypeOrdovician;
import net.lepidodendron.world.biome.ordovician.BiomeOrdovician;
import net.lepidodendron.world.gen.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeOrdovicianSpongeForest extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:ordovician_sponge_forest")
	public static final BiomeGenCustom biome = null;
	public BiomeOrdovicianSpongeForest(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.OCEAN);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.WATER);
	}

	static class BiomeGenCustom extends BiomeOrdovician {
		public BiomeGenCustom() {
			super(new BiomeProperties("Ordovician Sponge Forest").setRainfall(0.5F).setBaseHeight(-1.0F).setHeightVariation(0.05F).setTemperature(0.8F));
			setRegistryName("lepidodendron:ordovician_sponge_forest");
			topBlock = Blocks.GRAVEL.getDefaultState();
			fillerBlock = Blocks.GRAVEL.getDefaultState();
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 2;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}

		protected static final WorldGenNullTree NULL_TREE = new WorldGenNullTree(false);
		protected static final WorldGenRockPiles ROCK_PILES_GENERATOR = new WorldGenRockPiles();
    	protected static final WorldGenDollyphyton DOLLYPHYTON_GENERATOR = new WorldGenDollyphyton();
		protected static final WorldGenEdwardsiphyton EDWARDSIPHYTON_GENERATOR = new WorldGenEdwardsiphyton();
		protected static final WorldGenReef REEF_GENERATOR = new WorldGenReef();
		protected static final WorldGenAulaceraSponge AULACERA_GENERATOR = new WorldGenAulaceraSponge();
		protected static final WorldGenThamnobeatriceaSponge THAMNOBEATRICEA_GENERATOR = new WorldGenThamnobeatriceaSponge();

		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
			return NULL_TREE;
	    }

		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
	        {
	        	int i = rand.nextInt(2);
	            for (int j = 0; j < i; ++j)
	            {
	                int k = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
	                ROCK_PILES_GENERATOR.generate(worldIn, rand, blockpos);
	            }
	        }
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 30; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            DOLLYPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 30; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            EDWARDSIPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
			{
				for (int i = 0; i < 192; ++i)
				{
					//if (rand.nextInt(6) == 0) {
						int j = rand.nextInt(16) + 8;
						int k = rand.nextInt(16) + 8;
						int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
						BlockPos pos1 = pos.add(j, l, k);
						if (
								(pos1.getY() < worldIn.getSeaLevel() - 1)
										&& (worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
										&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
						) {
							AULACERA_GENERATOR.generate(worldIn, rand, pos1);
						}
					//}
				}

				for (int i = 0; i < 92; ++i)
				{
					//if (rand.nextInt(6) == 0) {
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					BlockPos pos1 = pos.add(j, l, k);
					if (
							(pos1.getY() < worldIn.getSeaLevel() - 1)
									&& (worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
									&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
					) {
						THAMNOBEATRICEA_GENERATOR.generate(worldIn, rand, pos1);
					}
					//}
				}
			}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
				for (int i = 0; i < 4; ++i)
				{
					int radius = 2;
					int j;
					int k;
					if (radius < 10) {
						j = 16 + (int)Math.floor(rand.nextInt(16 - radius - 6)/2) - (int)Math.floor(rand.nextInt(16 - radius - 6)/2);
						k = 16 + (int)Math.floor(rand.nextInt(16 - radius - 6)/2) - (int)Math.floor(rand.nextInt(16 - radius - 6)/2);
					}
					else {
						radius = 10;
						j = 16;
						k = 16;
					}
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					BlockPos pos1 = pos.add(j, l, k);
					if (
							(pos1.getY() < worldIn.getSeaLevel())
					) {
						REEF_GENERATOR.generate(worldIn, rand, pos1, radius, BlockStromatoporoideaReef.block.getDefaultState());
					}
				}
	        super.decorate(worldIn, rand, pos);
		}

		@Override
		public EnumBiomeTypeOrdovician getBiomeType() {
			return EnumBiomeTypeOrdovician.Sponge;
		}
	}

}
