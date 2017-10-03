package game;

import java.util.ArrayList;
import org.newdawn.slick.Input;

import sprites.Position;
import sprites.effects.Effect;
import sprites.effects.Explosion;
import sprites.effects.Kick;
import sprites.effects.Lightning;
import sprites.effects.Poof;
import sprites.effects.Pop;
import sprites.effects.SonicBoom;

// stores and renders game effects and animations such as Explosion
public class GameEffects {
  private ArrayList<Effect> effects;
  private ArrayList<Effect> effectsToAdd;
  private ArrayList<Effect> effectsToRemove;

  public GameEffects() {
    effects = new ArrayList<>();
    effectsToAdd = new ArrayList<>();
    effectsToRemove = new ArrayList<>();
  }
  
  // adds the given effect to effects to be rendered
  public void showEffect(Effect effect, Position position) {
    effectsToAdd.add(effect);
    effect.makeSound();
  }
  public void showPop(Position position) {
    Pop pop = new Pop(position);
    showEffect(pop, position);
  }
  
  public void showExplosion(Position position) {
    Explosion explosion = new Explosion(position);
    showEffect(explosion, position);
  }
  
  public void showPoof(Position position) {
    Poof poof = new Poof(position);
    showEffect(poof, position);
  }
  
  public void showLightning(Position position) {
    Lightning lightning = new Lightning(position);
    showEffect(lightning, position);
  }
  
  public void throwSonicBoom(Position position) {
    SonicBoom sonicBoom = new SonicBoom(position);
    effects.add(sonicBoom);
    sonicBoom.makeSound();
  }
  
  public void throwKick(Position position) {
    Kick kick = new Kick(position);
    showEffect(kick, position);
  }
  
  // adds new effects from queue, updates current effects and removes effects that have finished playing from effects
  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (!effectsToAdd.isEmpty()) {
      effects.addAll(effectsToAdd);
      effectsToAdd.clear();;
    }

    if (!effects.isEmpty()) {
      for (Effect effect : effects) {
        effect.update(input, delta, properties, assets);
        if (effect.isFinished()) {
          effectsToRemove.add(effect);
        }
      }
    }
    
    if (!effectsToRemove.isEmpty()) {
      effects.removeAll(effectsToRemove);
      effectsToRemove.clear();;
    }
  }
  
  public ArrayList<Effect> getEffects() {
    return effects;
  }
}

