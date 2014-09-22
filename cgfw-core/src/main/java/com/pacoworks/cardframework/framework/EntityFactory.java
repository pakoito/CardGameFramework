
package com.pacoworks.cardframework.framework;

import lombok.NonNull;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.EntityBuilder;
import com.pacoworks.cardframework.components.GamePhases;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
public class EntityFactory {
    public static Entity createGame(World world, @NonNull BasePhaseSystem phaseSystems) {
        EntityBuilder entityBuilder = new EntityBuilder(world);
        entityBuilder.with(new GamePhases(phaseSystems));
        entityBuilder.group(Constants.Groups.FRAMEWORK);
        entityBuilder.tag(Constants.Tags.GAME);
        return entityBuilder.build();
    }
    /*
     * public static Entity createHero(World world, String sprite, FloatPair startPosition,
     * Constants.Players owner, float rotation, int healthPoints, float speed, float rotationSpeed,
     * float collisionAngle) { Entity myLittleSprite = world.createEntity();
     * myLittleSprite.addComponent(new Sprite(sprite, Layer.ACTORS_1));
     * myLittleSprite.addComponent(new MapPosition(startPosition)); myLittleSprite.addComponent(new
     * Rotation(rotation)); myLittleSprite.addComponent(new Owner(owner));
     * myLittleSprite.addComponent(new Health(healthPoints)); myLittleSprite.addComponent(new
     * MovementSpeed(speed)); myLittleSprite.addComponent(new RotationSpeed(rotationSpeed));
     * myLittleSprite.addComponent(new CollisionAngle(collisionAngle));
     * world.getManager(TagManager.class).register(sprite, myLittleSprite);
     * world.getManager(GroupManager.class).add(myLittleSprite,
     * Constants.Groups.PLAYER_ONE_CREATURE); world.addEntity(myLittleSprite); return
     * myLittleSprite; } public static Entity createEnemy(World world, String sprite, FloatPair
     * startPosition, FloatPair targetPosition, float rotation, int healthPoints, int killScore,
     * float moveSpeed) { FloatPair velocityPair = MathGameUtils.getVelocityPair(startPosition,
     * targetPosition, moveSpeed); return new EntityBuilder(world) .with(new Sprite(sprite,
     * Layer.ACTORS_3), new MapPosition(startPosition), new Rotation(rotation), new
     * PositionTarget(targetPosition), new Velocity(velocityPair), new Health(healthPoints), new
     * KillScore(killScore)).group(Constants.Groups.CREATURE).build(); } public static Entity
     * createMcGuffin(World world) { return new EntityBuilder(world) .with(new
     * Sprite(Constants.MCGUFFIN_TAG, Layer.BACKGROUND), new MapPosition(new
     * FloatPair(Constants.GAME_MIDDLE_X, Constants.GAME_MIDDLE_Y)), new Rotation(0), new
     * Health(20)) .tag(Constants.MCGUFFIN_TAG).group(Constants.Groups.PLAYER_NEUTRAL).build(); }
     * public static Entity createYouWin(World world) { return new EntityBuilder(world) .with(new
     * Sprite(Constants.YOU_WIN_TAG, Layer.PARTICLES), new MapPosition(new
     * FloatPair(Constants.GAME_MIDDLE_X, Constants.GAME_MIDDLE_Y)), new Rotation(0))
     * .tag(Constants.YOU_WIN_TAG).group(Constants.Groups.UI).build(); } public static Entity
     * createYouLose(World world) { return new EntityBuilder(world) .with(new
     * Sprite(Constants.YOU_LOSE_TAG, Layer.PARTICLES), new MapPosition(new
     * FloatPair(Constants.GAME_MIDDLE_X, Constants.GAME_MIDDLE_Y)), new Rotation(0))
     * .tag(Constants.YOU_LOSE_TAG).group(Constants.Groups.UI).build(); }
     */
}
