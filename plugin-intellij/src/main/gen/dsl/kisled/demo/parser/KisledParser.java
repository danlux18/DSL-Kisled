// This is a generated file. Not intended for manual editing.
package dsl.kisled.demo.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dsl.kisled.demo.psi.KisledTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class KisledParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return kisledFile(b, l + 1);
  }

  /* ********************************************************** */
  // KON | KLONG | KSHORT | KOFF
  public static boolean act_signal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "act_signal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACT_SIGNAL, "<act signal>");
    r = consumeToken(b, KON);
    if (!r) r = consumeToken(b, KLONG);
    if (!r) r = consumeToken(b, KSHORT);
    if (!r) r = consumeToken(b, KOFF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NAME LEFT act_signal |  NAME LEFT act_signal KTIMES TIMES
  public static boolean action(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = action_0(b, l + 1);
    if (!r) r = action_1(b, l + 1);
    exit_section_(b, m, ACTION, r);
    return r;
  }

  // NAME LEFT act_signal
  private static boolean action_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NAME, LEFT);
    r = r && act_signal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NAME LEFT act_signal KTIMES TIMES
  private static boolean action_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NAME, LEFT);
    r = r && act_signal(b, l + 1);
    r = r && consumeTokens(b, 0, KTIMES, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // action actions | action
  public static boolean actions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = actions_0(b, l + 1);
    if (!r) r = action(b, l + 1);
    exit_section_(b, m, ACTIONS, r);
    return r;
  }

  // action actions
  private static boolean actions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = action(b, l + 1);
    r = r && actions(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KACTUATOR NAME? ASSIGN PORT | KSENSOR NAME? ASSIGN PORT
  public static boolean brick(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "brick")) return false;
    if (!nextTokenIs(b, "<brick>", KACTUATOR, KSENSOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BRICK, "<brick>");
    r = brick_0(b, l + 1);
    if (!r) r = brick_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KACTUATOR NAME? ASSIGN PORT
  private static boolean brick_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "brick_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KACTUATOR);
    r = r && brick_0_1(b, l + 1);
    r = r && consumeTokens(b, 0, ASSIGN, PORT);
    exit_section_(b, m, null, r);
    return r;
  }

  // NAME?
  private static boolean brick_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "brick_0_1")) return false;
    consumeToken(b, NAME);
    return true;
  }

  // KSENSOR NAME? ASSIGN PORT
  private static boolean brick_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "brick_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KSENSOR);
    r = r && brick_1_1(b, l + 1);
    r = r && consumeTokens(b, 0, ASSIGN, PORT);
    exit_section_(b, m, null, r);
    return r;
  }

  // NAME?
  private static boolean brick_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "brick_1_1")) return false;
    consumeToken(b, NAME);
    return true;
  }

  /* ********************************************************** */
  // brick bricks | brick
  public static boolean bricks(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bricks")) return false;
    if (!nextTokenIs(b, "<bricks>", KACTUATOR, KSENSOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BRICKS, "<bricks>");
    r = bricks_0(b, l + 1);
    if (!r) r = brick(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // brick bricks
  private static boolean bricks_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bricks_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = brick(b, l + 1);
    r = r && bricks(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NAME signal
  public static boolean condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NAME);
    r = r && signal(b, l + 1);
    exit_section_(b, m, CONDITION, r);
    return r;
  }

  /* ********************************************************** */
  // condition KAND conditions | condition
  public static boolean conditions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditions")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = conditions_0(b, l + 1);
    if (!r) r = condition(b, l + 1);
    exit_section_(b, m, CONDITIONS, r);
    return r;
  }

  // condition KAND conditions
  private static boolean conditions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditions_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = condition(b, l + 1);
    r = r && consumeToken(b, KAND);
    r = r && conditions(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INITSTATE NAME
  public static boolean init_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "init_state")) return false;
    if (!nextTokenIs(b, INITSTATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INITSTATE, NAME);
    exit_section_(b, m, INIT_STATE, r);
    return r;
  }

  /* ********************************************************** */
  // bricks states init_state | COMMENTS
  static boolean kisledFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kisledFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = kisledFile_0(b, l + 1);
    if (!r) r = consumeToken(b, COMMENTS);
    exit_section_(b, m, null, r);
    return r;
  }

  // bricks states init_state
  private static boolean kisledFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kisledFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bricks(b, l + 1);
    r = r && states(b, l + 1);
    r = r && init_state(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KHIGH | KLOW
  public static boolean signal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signal")) return false;
    if (!nextTokenIs(b, "<signal>", KHIGH, KLOW)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIGNAL, "<signal>");
    r = consumeToken(b, KHIGH);
    if (!r) r = consumeToken(b, KLOW);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NAME BLOCK_START actions transitions BLOCK_END
  public static boolean state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NAME, BLOCK_START);
    r = r && actions(b, l + 1);
    r = r && transitions(b, l + 1);
    r = r && consumeToken(b, BLOCK_END);
    exit_section_(b, m, STATE, r);
    return r;
  }

  /* ********************************************************** */
  // state states | state
  public static boolean states(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = states_0(b, l + 1);
    if (!r) r = state(b, l + 1);
    exit_section_(b, m, STATES, r);
    return r;
  }

  // state states
  private static boolean states_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = state(b, l + 1);
    r = r && states(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // conditions RIGHT NAME
  public static boolean transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = conditions(b, l + 1);
    r = r && consumeTokens(b, 0, RIGHT, NAME);
    exit_section_(b, m, TRANSITION, r);
    return r;
  }

  /* ********************************************************** */
  // transition transitions | transition
  public static boolean transitions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions")) return false;
    if (!nextTokenIs(b, NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transitions_0(b, l + 1);
    if (!r) r = transition(b, l + 1);
    exit_section_(b, m, TRANSITIONS, r);
    return r;
  }

  // transition transitions
  private static boolean transitions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transition(b, l + 1);
    r = r && transitions(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
