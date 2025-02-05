const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/invertir/titulos/fci`;

export async function getCedears() {
  const res = await fetch(`${baseUrl}`);
  const data = await res.json();
  return data.data;
}
