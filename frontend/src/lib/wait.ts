export function wait(ms: number) {
  return new Promise((res) => {
    setTimeout(() => {
      res(true);
    }, ms);
  });
}
