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

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
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
                .type(BLOCK)
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
    
    
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return entityBuilder()
                .view((texture("background/classe.png")))
                .zIndex(-1)
                .build();
    }
    
    @Spawns("boardMenu")
    public Entity newboardMenu(SpawnData data) {
        return entityBuilder()
                .view((texture("tableau.png")))
                .zIndex(0)
                .build();
    }
    
    @Spawns("chair")
    public Entity newchair(SpawnData data) {
        return entityBuilder()
                .view((texture("table.png")))
                .zIndex(1)
                .build();
    }
    
//    @Spawns("newGameTitle")
//    public Entity newnewGameTitle(SpawnData data) {
//        return entityBuilder()
//                .view()
//                .zIndex(0)
//                .build();
//    }
}
