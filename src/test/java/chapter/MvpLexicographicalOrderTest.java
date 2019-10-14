package chapter;

class MvpLexicographicalOrderTest extends LexicographicalOrderAbstractTest {

  @Override
  public LexicographicalOrder getSut() {
    return new LexicographicalOrderMvpImpl();
  }

}