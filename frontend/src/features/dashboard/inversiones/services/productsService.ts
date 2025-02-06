const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/productos`;

export async function getProducts() {
  const res = await fetch(`${baseUrl}`);
  const data = await res.json();
  return data;
}
