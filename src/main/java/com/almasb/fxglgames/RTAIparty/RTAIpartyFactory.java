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
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxglgames.RTAIparty.components.DodgePlayerComponent;
import com.almasb.fxglgames.RTAIparty.components.DodgeProjectileComponent;
import com.almasb.fxglgames.RTAIparty.components.MemoryCardComponent;
import com.almasb.fxglgames.RTAIparty.components.RythmNumberComponent;

import javafx.geometry.Point2D;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxglgames.RTAIparty.RTAIpartyApp.BLOCK_SIZE;
import static com.almasb.fxglgames.RTAIparty.RTAIpartyType.*;

import java.util.Random;


/**
 * Factory for creating in-game entities.
 *
 * @author GROUPE5
 * Classes permettant de créer dfférentes entitées en fonction du nom du Spawn
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
    	var view = texture("garcon_blond_dodge.png");
    	PhysicsComponent physics = new PhysicsComponent();
    	
        var e = entityBuilder(data)
                .type(DODGE_PLAYER)
                .bbox(new HitBox(new Point2D(0, 16), BoundingShape.box(32, 32)))
                .view(view)
                .zIndex(3)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new DodgePlayerComponent())
                .build();

        e.setLocalAnchorFromCenter();

        return e;
    }
    
    @Spawns("projectileDodge")
    public Entity newDodgeProjectile(SpawnData data) {
    	Random randomTexture = new Random();
    	int iRandomTexture;
    	iRandomTexture = randomTexture.nextInt(2);
    	
    	var view = texture("screen.png");
    	switch(iRandomTexture){
        case 0 : 
        	view = texture("screen.png");
            break; // Here
        case 1 :
        	view = texture("mouse.png");
            break; // And here
    	}
    	
    	PhysicsComponent physics = new PhysicsComponent();

        var e = entityBuilder(data)
                .type(DODGE_PROJECTILE)
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(64, 64)))
                .view(view)
                .zIndex(3)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new DodgeProjectileComponent())
                .build();

        e.setLocalAnchorFromCenter();

        return e;
    }
    

    @Spawns("decor")
    public Entity newdecor(SpawnData data) {
        return entityBuilder()
                .view((texture("background/decorrythm.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("decorclasse")
    public Entity newdecorclasse(SpawnData data) {
        return entityBuilder()
                .view((texture("background/classe.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("decortableau")
    public Entity newdecortableau(SpawnData data) {
        return entityBuilder()
                .view((texture("tableau.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("numero1")
    public Entity newnumero1(SpawnData data) {
    	RythmNumberComponent componentNum1 = new RythmNumberComponent();
    	componentNum1.AssignNumber(1);
    	
        return entityBuilder()
                .view((texture("Numero/numero1.png")))
                .zIndex(1)
                .with(componentNum1)
                .build();
    }
    
    @Spawns("numero2")
    public Entity newnumero2(SpawnData data) {
    	RythmNumberComponent componentNum2 = new RythmNumberComponent();
    	componentNum2.AssignNumber(2);
    	
    	return entityBuilder()
                .view((texture("Numero/numero2.png")))
                .zIndex(1)
                .with(componentNum2)
                .build();
    }
    
    
    @Spawns("numero3")
    public Entity newnumero3(SpawnData data) {
    	
    	RythmNumberComponent componentNum3 = new RythmNumberComponent();
    	componentNum3.AssignNumber(3);
        return entityBuilder()
                .view((texture("Numero/numero3.png")))
                .zIndex(1)
                .with(componentNum3)
                .build();
    }
        
    @Spawns("numero4")
    public Entity newnumero4(SpawnData data) {
    	
    	RythmNumberComponent componentNum4 = new RythmNumberComponent();
    	componentNum4.AssignNumber(4);
    	
        return entityBuilder()
                .view((texture("Numero/numero4.png")))
                .zIndex(1)
                .with(componentNum4)
                .build();
    }
    
    @Spawns("numero5")
    public Entity newnumero5(SpawnData data) {
    	
    	RythmNumberComponent componentNum5 = new RythmNumberComponent();
    	componentNum5.AssignNumber(5);
    	
        return entityBuilder()
                .view((texture("Numero/numero5.png")))
                .zIndex(1)
                .with(componentNum5)
                .build();
    }
    
    @Spawns("numero6")
    public Entity newnumero6(SpawnData data) {
    	
    	RythmNumberComponent componentNum6 = new RythmNumberComponent();
    	componentNum6.AssignNumber(6);
    	
        return entityBuilder()
                .view((texture("Numero/numero6.png")))
                .zIndex(1)
                .with(componentNum6)
                .build();
    }
    
    @Spawns("numero7")
    public Entity newnumero7(SpawnData data) {
    	
    	RythmNumberComponent componentNum7 = new RythmNumberComponent();
    	componentNum7.AssignNumber(7);
    	
        return entityBuilder()
                .view((texture("Numero/numero7.png")))
                .zIndex(1)
                .with(componentNum7)
                .build();
    }
    
    @Spawns("numero8")
    public Entity newnumero8(SpawnData data) {
    	
    	RythmNumberComponent componentNum8 = new RythmNumberComponent();
    	componentNum8.AssignNumber(8);
    	
        return entityBuilder()
                .view((texture("Numero/numero8.png")))
                .zIndex(1)
                .with(componentNum8)
                .build();
    }
    
    
    @Spawns("carteGBlond")
    public Entity newcarteGBlond(SpawnData data) {
    	
    	MemoryCardComponent MemoryCard = new MemoryCardComponent();
        return entityBuilder()
                .view((texture("cartegarconblond.png")))
                .zIndex(1)
                .with(MemoryCard)
                .build();
    }
    
    @Spawns("carteGBrun")
    public Entity newcarteGBrun(SpawnData data) {
    	
    	MemoryCardComponent MemoryCard = new MemoryCardComponent();
        return entityBuilder()
                .view((texture("cartegarconbrun.png")))
                .zIndex(1)
                .with(MemoryCard)
                .build();
    }
    
    @Spawns("carteFBlonde")
    public Entity newcarteFBlonde(SpawnData data) {
    	
    	MemoryCardComponent MemoryCard = new MemoryCardComponent();
        return entityBuilder()
                .view((texture("cartefilleblonde.png")))
                .zIndex(1)
                .with(MemoryCard)
                .build();
    }
    
    @Spawns("carteFBrune")
    public Entity newcarteFBrune(SpawnData data) {
    	
    	MemoryCardComponent MemoryCard = new MemoryCardComponent();
        return entityBuilder()
                .view((texture("cartefillebrune.png")))
                .zIndex(1)
                .with(MemoryCard)
                .build();
    }
    
    
    @Spawns("EndGBlond")
    public Entity newEndGBlond(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("garcon_blond.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("EndGBrun")
    public Entity newEndGBrun(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("garcon_brun.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("EndFBlonde")
    public Entity newEndFBlonde(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("fille_blonde.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("EndFBrune")
    public Entity newEndFBrune(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("fille_brune.png")))
                .zIndex(1)
                .build();
    }
    
    
    @Spawns("arrow")
    public Entity newarrow(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("arrow.png")))
                .zIndex(3)
                .build();
    }
    
    
    @Spawns("decordodge")
    public Entity newdecordodge(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("background/decordodge.png")))
                .zIndex(1)
                .build();
    }
    
    @Spawns("win")
    public Entity newinfowin(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("win.png")))
                .zIndex(8)
                .build();
    }
    
    @Spawns("loose")
    public Entity newinfoloose(SpawnData data) {
    	
        return entityBuilder()
                .view((texture("loose.png")))
                .zIndex(8)
                .build();
    }
    
    
    
}
