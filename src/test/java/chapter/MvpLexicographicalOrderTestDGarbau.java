package chapter;

class MvpLexicographicalOrderTestDGarbau extends LexicographicalOrderAbstractTest {

  @Override
  public LexicographicalOrder getSut() {
    return new LexicographicalOrderMvpImplDGarbau();
  }

}