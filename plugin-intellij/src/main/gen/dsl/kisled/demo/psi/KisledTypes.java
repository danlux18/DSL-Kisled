// This is a generated file. Not intended for manual editing.
package dsl.kisled.demo.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dsl.kisled.demo.psi.impl.*;

public interface KisledTypes {

  IElementType ACTION = new KisledElementType("ACTION");
  IElementType ACTIONS = new KisledElementType("ACTIONS");
  IElementType ACT_SIGNAL = new KisledElementType("ACT_SIGNAL");
  IElementType BRICK = new KisledElementType("BRICK");
  IElementType BRICKS = new KisledElementType("BRICKS");
  IElementType CONDITION = new KisledElementType("CONDITION");
  IElementType CONDITIONS = new KisledElementType("CONDITIONS");
  IElementType INIT_STATE = new KisledElementType("INIT_STATE");
  IElementType SIGNAL = new KisledElementType("SIGNAL");
  IElementType STATE = new KisledElementType("STATE");
  IElementType STATES = new KisledElementType("STATES");
  IElementType TRANSITION = new KisledElementType("TRANSITION");
  IElementType TRANSITIONS = new KisledElementType("TRANSITIONS");

  IElementType ASSIGN = new KisledTokenType("ASSIGN");
  IElementType BLOCK_END = new KisledTokenType("BLOCK_END");
  IElementType BLOCK_START = new KisledTokenType("BLOCK_START");
  IElementType COMMENTS = new KisledTokenType("COMMENTS");
  IElementType INITSTATE = new KisledTokenType("INITSTATE");
  IElementType KACTUATOR = new KisledTokenType("KACTUATOR");
  IElementType KAND = new KisledTokenType("KAND");
  IElementType KHIGH = new KisledTokenType("KHIGH");
  IElementType KLONG = new KisledTokenType("KLONG");
  IElementType KLOW = new KisledTokenType("KLOW");
  IElementType KOFF = new KisledTokenType("KOFF");
  IElementType KON = new KisledTokenType("KON");
  IElementType KSENSOR = new KisledTokenType("KSENSOR");
  IElementType KSHORT = new KisledTokenType("KSHORT");
  IElementType KTIMES = new KisledTokenType("KTIMES");
  IElementType LEFT = new KisledTokenType("LEFT");
  IElementType NAME = new KisledTokenType("NAME");
  IElementType PORT = new KisledTokenType("PORT");
  IElementType RIGHT = new KisledTokenType("RIGHT");
  IElementType TIMES = new KisledTokenType("TIMES");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACTION) {
        return new KisledActionImpl(node);
      }
      else if (type == ACTIONS) {
        return new KisledActionsImpl(node);
      }
      else if (type == ACT_SIGNAL) {
        return new KisledActSignalImpl(node);
      }
      else if (type == BRICK) {
        return new KisledBrickImpl(node);
      }
      else if (type == BRICKS) {
        return new KisledBricksImpl(node);
      }
      else if (type == CONDITION) {
        return new KisledConditionImpl(node);
      }
      else if (type == CONDITIONS) {
        return new KisledConditionsImpl(node);
      }
      else if (type == INIT_STATE) {
        return new KisledInitStateImpl(node);
      }
      else if (type == SIGNAL) {
        return new KisledSignalImpl(node);
      }
      else if (type == STATE) {
        return new KisledStateImpl(node);
      }
      else if (type == STATES) {
        return new KisledStatesImpl(node);
      }
      else if (type == TRANSITION) {
        return new KisledTransitionImpl(node);
      }
      else if (type == TRANSITIONS) {
        return new KisledTransitionsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
