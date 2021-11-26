/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.fxglgames.RTAIparty;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxglgames.RTAIparty.components.DodgePlayerComponent;
import com.almasb.fxglgames.RTAIparty.components.DodgeProjectileComponent;

import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxglgames.RTAIparty.RTAIpartyApp.BLOCK_SIZE;
import static com.almasb.fxglgames.RTAIparty.RTAIpartyType.*;


/**
 * Factory for creating in-game entities.
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class RTAIpartyFactory implements EntityFactory {
	
    @Spawns("0")
    public Entity newBlockBleu(SpawnData data) {
        var view = texture("DodgeBlock.png");

        return entityBuilder(data)

                .view(view)
                .zIndex(-1)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 32))
                .build();
    }
    
    @Spawns("1")
    public Entity newBlockRouge(SpawnData data) {
        var view = texture("block-rouge.png");

        return entityBuilder(data)
                .type(BLOCK)
                .view(view)
                .zIndex(-1)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 32))
                .build();
    }

    @Spawns("2")
    public Entity newBlockViolet(SpawnData data) {
        var view = texture("block-violet.png");

        return entityBuilder(data)
                .type(BLOCK)
                .view(view)
                .zIndex(-1)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 32))
                .build();
    }
    
    
    
    @Spawns("playerDodge")
    public Entity newDodgePlayer(SpawnData data) {
    	var view = texture("garcon_dodge.png");

        var e = entityBuilder(data)
                .type(DODGE_PLAYER)
                .bbox(new HitBox(new Point2D(0, 16), BoundingShape.box(32, 32)))
                .view(view)
                .zIndex(3)
                .with(new CollidableComponent(true))
                .with(new DodgePlayerComponent())
                .build();

        e.setLocalAnchorFromCenter();

        return e;
    }
    
    @Spawns("projectileDodge")
    public Entity newDodgeProjectile(SpawnData data) {
    	var view = texture("screen.png");

        var e = entityBuilder(data)
                .type(DODGE_PROJECTILE)
                .bbox(new HitBox(new Point2D(0, 32), BoundingShape.box(64, 64)))
                .view(view)
                .zIndex(3)
                .with(new CollidableComponent(true))
                .with(new DodgeProjectileComponent())
                .build();

        e.setLocalAnchorFromCenter();

        return e;
    }
    
    
//    @Spawns("newGameTitle")
//    public Entity newnewGameTitle(SpawnData data) {
//        return entityBuilder()
//                .view()
//                .zIndex(0)
//                .build();
//    }
    @Spawns("decor")
    public Entity newdecor(SpawnData data) {
        return entityBuilder()
                .view((texture("background/decorrythm.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("numero1")
    public Entity newnumero1(SpawnData data) {
        return entityBuilder()
                .view((texture("Numero/numero1.png")))
                .zIndex(1)
                .build();
    }
    
    
    @Spawns("decordodge")
    public Entity newdecordodge(SpawnData data) {
        return entityBuilder()
                .view((texture("background/decordodge.png")))
                .zIndex(1)
                .build();
    }
}
