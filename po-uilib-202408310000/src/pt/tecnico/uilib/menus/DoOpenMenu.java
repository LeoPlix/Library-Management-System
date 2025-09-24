package pt.tecnico.uilib.menus;

import java.util.function.Predicate;

/**
 * Command to open a menu.
 */
public class DoOpenMenu extends Command<Menu> {

  /**
   * @param receiver
   */
  public DoOpenMenu(String label, Menu receiver) {
    super(label, receiver);
  }

  @Override
  protected final void execute() {
    _receiver.open();
  }

}
